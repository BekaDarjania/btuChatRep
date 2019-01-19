package com.dabekka.btuchat

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dabekka.btuchat.R.layout.activity_chat
import com.dabekka.btuchat.R.layout.activity_register
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if(currentUser != null){
            val intent = Intent(this@LoginActivity, LogoutActivity::class.java)
            startActivity(intent)
        }
    }

    fun init() {
        // set up buttons
        loginButton.setOnClickListener {
            login()
        }

        registerInstead.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {

        if (mailField.text.isEmpty() || pwdField.text.isEmpty()) {
            Toast.makeText(applicationContext, "some field(s) was empty", Toast.LENGTH_SHORT).show()
        } else {
            val mail = mailField.text.toString()
            val pwd = pwdField.text.toString()

            auth.signInWithEmailAndPassword(mail, pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this@LoginActivity, ChatActivity::class.java)
                        startActivity(intent)
                        val user = auth.currentUser
                        // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // updateUI(null)
                    }
                }
        }
    }
}
