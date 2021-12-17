package com.ops.flipclass.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ops.flipclass.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_teacher.*
import kotlinx.android.synthetic.main.app_toolbar.*

class TeacherActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

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

        mSharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        editor = mSharedPreferences.edit()

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


            editor.putString("userName", userName)
            editor.putString("userGivenName", userGivenName)
            editor.putString("userFamilyName", userFamilyName)
            editor.putString("userEmail", userEmail)
            editor.putString("userId", userId)
            editor.putString("userPhoto", (userPhoto).toString())
            editor.apply()

            tv_userName.text = mSharedPreferences.getString("userName", "Error")
            val photo = mSharedPreferences.getString("userPhoto", "Error")
            Glide.with(this).load((photo).toString()).into(civ_user_image)
        }

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

        llUserImage.setOnClickListener {
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
            val intent = Intent(
                this@TeacherActivity,
                LoginActivity::class.java
            )/*setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)*/
            editor.clear()
            editor.apply()
            Toast.makeText(this@TeacherActivity, "Signed Out Successfully", Toast.LENGTH_SHORT)
                .show()
            startActivity(intent)
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