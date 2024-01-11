package com.example.messengerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val username: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val loginBtn: Button = findViewById(R.id.loginBtn)
        val registerBtn : Button = findViewById(R.id.registerBtn)

        loginBtn.setOnClickListener(View.OnClickListener {
            // Replace this with your authentication logic
            if (isValidCredentials(username.text.toString(), password.text.toString())) {
                // If the credentials are valid, start the main activity
                var preferenceManager = PreferenceManager(this)
                preferenceManager.setLoggedIn(true)
                startMainActivity()
            } else {
                // If the credentials are not valid, you can show an error message
                // For simplicity, let's just clear the password field
                password.text.clear()
            }
        })

        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // Implement your authentication logic here
        // For simplicity, let's say the credentials are valid if the username and password are not empty

        return true//!username.isEmpty() && !password.isEmpty()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the login activity so the user cannot go back to it using the back button
    }
}
