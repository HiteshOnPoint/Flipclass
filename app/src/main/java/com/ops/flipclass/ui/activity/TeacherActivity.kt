package com.ops.flipclass.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ops.flipclass.R
import com.ops.flipclass.utilities.Infrastructure
import com.ops.flipclass.utilities.SharedPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_teacher.*
import kotlinx.android.synthetic.main.app_toolbar.*

class TeacherActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/
        
        tv_hello.visibility = View.VISIBLE
        tv_loggedInUserName.visibility = View.VISIBLE
        civ_loggedInUserImage.visibility = View.VISIBLE



        /*mSharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        editor = mSharedPreferences.edit()*/

        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)*/

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val userName = acct.displayName
            val userGivenName = acct.givenName
            val userFamilyName = acct.familyName
            val userEmail = acct.email
            val userId = acct.id
            val userPhoto: Uri? = acct.photoUrl


            SharedPrefsUtils.setStringPreference(this,"userName", userName)
            SharedPrefsUtils.setStringPreference(this,"userGivenName", userGivenName)
            SharedPrefsUtils.setStringPreference(this,"userFamilyName", userFamilyName)
            SharedPrefsUtils.setStringPreference(this,"userEmail", userEmail)
            SharedPrefsUtils.setStringPreference(this,"userId", userId)
            SharedPrefsUtils.setStringPreference(this,"userPhoto", (userPhoto).toString())

            tv_loggedInUserName.text = SharedPrefsUtils.getStringPreference(this,"userName")
            val photo = SharedPrefsUtils.getStringPreference(this,"userPhoto")
            Glide.with(this).load(photo).into(civ_loggedInUserImage)
        }

        Infrastructure.dismissProgressDialog()

        mAuth = FirebaseAuth.getInstance()

        val uid = mAuth.currentUser?.uid

        /*mDbRef = FirebaseDatabase.getInstance("https://flipclass-d87e8-default-rtdb.firebaseio.com/").reference

        mDbRef.child("user").child(uid!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("name").value.toString()
                tv_userName.text = name
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })*/

        llMessages.setOnClickListener {
            startActivity(Intent(this@TeacherActivity, MessageActivity::class.java))
        }

        civ_loggedInUserImage.setOnClickListener {
            startActivity(Intent(this@TeacherActivity, MyProfileActivity::class.java))
        }

        llLogout.setOnClickListener {

            /*mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, object : OnCompleteListener<Void?> {
                    override fun onComplete(task: Task<Void?>) {
                        Toast.makeText(
                            this@TeacherActivity,
                            "Signed Out Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this@TeacherActivity, LoginActivity::class.java))
                        finish()
                    }
                })*/

            mAuth.signOut()
            SharedPrefsUtils.clearSharedPreference(this@TeacherActivity)
            val intent = Intent(this@TeacherActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Toast.makeText(this@TeacherActivity, "Signed Out Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    /*private fun status (status: String){

        val reference = mDbRef.child("user").child(mAuth.currentUser?.uid!!)

        val map = HashMap<String, Any>()
        map["status"] = status

        reference.updateChildren(map)
    }

    override fun onResume() {
        super.onResume()
        status("online")
    }

    override fun onPause() {
        super.onPause()
        status("offline")
    }*/
}