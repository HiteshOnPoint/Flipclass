package com.ops.flipclass

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.core.app.ActivityCompat.startActivityForResult
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ops.flipclass.models.User
import kotlinx.android.synthetic.main.app_toolbar.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        /*window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT*/


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        llSignInInstructor.setOnClickListener {

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        //val account = GoogleSignIn.getLastSignedInAccount(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
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
                        val name = acct.displayName
                        val email = acct.email
                        val photo: Uri? = acct.photoUrl

                        addUserToDatabase(name, email, mAuth.currentUser?.uid!!, photo.toString())
                    }

                    // Signed in successfully, show authenticated UI.
                    startActivity(Intent(this@LoginActivity, TeacherActivity::class.java))
                    Toast.makeText(this@LoginActivity, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
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