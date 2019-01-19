package com.dabekka.btuchat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        inti()
        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        // updateUI(currentUser)
    }


    fun inti() {
        // set up buttons
        registerButton.setOnClickListener {
            register()
        }
        loginInsteadButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)

            // To pass any data to next activity
            // intent.putExtra("keyIdentifier", "")
            // start your next activity
            startActivity(intent)
        }
    }

    private fun register() {

        if (mailField.text.isEmpty() || pwdField1.text.isEmpty() || pwdField2.text.isEmpty()) {
            Toast.makeText(applicationContext, "some field(s) is empty", Toast.LENGTH_SHORT).show()
        } else {
            val mail = mailField.text.toString()
            val pwd = pwdField1.text.toString()
            val pwd2 = pwdField2.text.toString()

            // check if passwords match
            if (pwd != pwd2) {
                Toast.makeText(applicationContext, "passwords don't match", Toast.LENGTH_SHORT).show()
            } else {

                auth.createUserWithEmailAndPassword(mail, pwd)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(applicationContext, "createUserWithEmail:success", Toast.LENGTH_SHORT)
                                .show()
                            val user = auth.currentUser
                            // updateUI(user)

                            // sign up user and sign in
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            // To pass any data to next activity
                            intent.putExtra("keyIdentifier", "")
                            // start your next activity
                            startActivity(intent)


                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // updateUI(null)
                        }
                        // ...
                    }
            }
        }
    }
}
