package com.example.messengerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity





class SplashActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferenceManager = PreferenceManager(this)

        try {
            if (preferenceManager.isLoggedIn()) {
                startMainActivity()
            } else {
                startLoginActivity()
            }
        } catch (e: Exception) {
            Log.e("SplashActivity", "Error in onCreate", e)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
//        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
