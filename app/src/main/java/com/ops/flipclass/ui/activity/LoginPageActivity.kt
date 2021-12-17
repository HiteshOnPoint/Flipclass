package com.ops.flipclass.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up_page.*
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.ops.flipclass.R


class LoginPageActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var btnSignUp: AppCompatButton
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance()

        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_Password)
        btnLogin = findViewById(R.id.btn_Login)
        btnSignUp = findViewById(R.id.btn_SignUp)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto: Uri? = acct.photoUrl

            Toast.makeText(this@LoginPageActivity, "Name :$personName", Toast.LENGTH_SHORT).show()
        }

        btnSignUp.setOnClickListener{

            startActivity(Intent(this@LoginPageActivity, SignUpPageActivity::class.java ))
        }

        btnLogin.setOnClickListener {

                /*addOnCompleteListener(this, OnCompleteListener<Void?> {
                    // ...
                })*/

            /*val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){

                login(email, password)

            }else{
                Toast.makeText(this@LoginPageActivity, "Enter all Fields", Toast.LENGTH_SHORT)
                    .show()
            }*/
        }

    }

    private fun login(email: String, password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    startActivity(Intent(this@LoginPageActivity, TeacherActivity::class.java))
                    Toast.makeText(this@LoginPageActivity, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@LoginPageActivity,
                        "User does not exist",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}