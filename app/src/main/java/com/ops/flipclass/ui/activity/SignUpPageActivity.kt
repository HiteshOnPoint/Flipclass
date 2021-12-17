package com.ops.flipclass.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.ops.flipclass.R
import kotlinx.android.synthetic.main.activity_sign_up_page.*

class SignUpPageActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: AppCompatButton
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        mAuth = FirebaseAuth.getInstance()
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/
        etName = findViewById(R.id.et_Name)
        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_Password)
        btnSignUp = findViewById(R.id.btn_SignUp)

        btnSignUp.setOnClickListener{
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

                signUp(name, email, password)

            }else{
                Toast.makeText(this@SignUpPageActivity, "Enter all Fields", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun signUp(name: String, email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    startActivity(Intent(this@SignUpPageActivity, TeacherActivity::class.java))
                    Toast.makeText(this@SignUpPageActivity, "User Created Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@SignUpPageActivity,
                        "Something Went Wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    /*private fun addUserToDatabase(name: String, email: String, uid: String){

        mDbRef = FirebaseDatabase.getInstance("https://flipclass-d87e8-default-rtdb.firebaseio.com/").reference

        mDbRef.child("user").child(uid).setValue(User(name, email, uid, "offline"))
    }*/
}