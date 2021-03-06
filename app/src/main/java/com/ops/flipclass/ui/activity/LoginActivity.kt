package com.ops.flipclass.ui.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.ops.flipclass.APiState
import com.ops.flipclass.R
import com.ops.flipclass.models.LoginResponse
import com.ops.flipclass.models.User
import com.ops.flipclass.ui.activity.authorization.viewmodel.LoginViewModel
import com.ops.flipclass.utilities.Infrastructure
import com.ops.flipclass.utilities.SharedPrefConstants
import com.ops.flipclass.utilities.SharedPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        llSignInInstructor.setOnClickListener {

            Infrastructure.showProgressDialog(this@LoginActivity)
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        /*llSignInStudent.setOnClickListener{
            executeLogin()
        }
*/
        //val account = GoogleSignIn.getLastSignedInAccount(this)

        initObserver()

    }

    private fun initObserver() {
        viewModel.loginHoldLiveData.observe(this, Observer { state ->
            when (state) {
                is APiState.Loading -> {
                    Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_LONG).show()

                }
                is APiState.Error -> {
                    Toast.makeText(applicationContext, state.message, Toast.LENGTH_LONG).show()
                }
                is APiState.Success -> {
                    val data = state.data
                    Log.e(TAG, data.toString())
                    if (data.status == "success") {
                        if (data.clientData != null) {

                            loadLoginData(data)

                            //Toast.makeText(applicationContext, "Login Data Loaded.\n clientID: ${data.clientData?.clientId}\n timeZone: ${data.clientData?.timezone}", Toast.LENGTH_LONG).show()
                        } else {
                            Infrastructure.dismissProgressDialog()
                            Infrastructure.showToastMessage(this, data.message)
                        }
                    } else {
                        Infrastructure.dismissProgressDialog()
                        Infrastructure.showToastMessage(this, data.message)
                    }
                }
            }
        })
    }

    private fun loadLoginData(data: LoginResponse) {
        val gson = Gson()
        val userData = gson.toJson(data)
        SharedPrefsUtils.setStringPreference(this, SharedPrefConstants.USER_DETAILS, userData)
        SharedPrefsUtils.setStringPreference(this, SharedPrefConstants.ACCESS_TOKEN,data.clientData?.accessToekn)
        SharedPrefsUtils.setIntegerPreference(this, SharedPrefConstants.LOGIN_STATUS, 1)

        //Toast.makeText(applicationContext, "Login Data Loaded.\n clientID: ${data.clientData?.clientId}\n timeZone: ${data.clientData?.timezone}", Toast.LENGTH_LONG).show()

    Handler().postDelayed({
            startActivity(Intent(this@LoginActivity, TeacherActivity::class.java))
                                Toast.makeText(this@LoginActivity, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                                finish()
        }, 1000)
    }

    private fun executeLogin(userGivenName: String, userFamilyName: String, userId: String, userEmail: String) {

        viewModel.login(
            userGivenName, userFamilyName, userId, userEmail
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Infrastructure.dismissProgressDialog()

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)

                Infrastructure.showProgressDialog(this@LoginActivity)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val uid = mAuth.currentUser

                    val acct = GoogleSignIn.getLastSignedInAccount(this)
                    if (acct != null) {
                        val userGivenName = acct.givenName
                        val userFamilyName = acct.familyName
                        val userEmail = acct.email
                        val userId = acct.id
                        val userPhoto: Uri? = acct.photoUrl

                        executeLogin(userGivenName, userFamilyName, userId, userEmail)

                        //addUserToDatabase(name, email, mAuth.currentUser?.uid!!, photo.toString())
                    }

                    // Signed in successfully, show authenticated UI.

                } else {
                    // If sign in fails, display a message to the user.
                        Infrastructure.dismissProgressDialog()
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this@LoginActivity, "Sign In Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        //val account = GoogleSignIn.getLastSignedInAccount(this)

        val currentUser = mAuth.currentUser

        if (currentUser != null){
            startActivity(Intent(this@LoginActivity, TeacherActivity::class.java))
            finish()
        }else{
            Toast.makeText(this@LoginActivity, "Login Please", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUserToDatabase(name: String?, email: String?, uid: String, photo: String){

        mDbRef = FirebaseDatabase.getInstance("https://flipclass-d87e8-default-rtdb.firebaseio.com/").reference

        mDbRef.child("user").child(uid).setValue(User(name, email, uid, "offline", photo))
    }

    companion object{

        private const val RC_SIGN_IN = 123
    }
}