package com.example.messengerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val username: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val loginBtn: Button = findViewById(R.id.loginBtn)
        val registerBtn : Button = findViewById(R.id.registerBtn)

        loginBtn.setOnClickListener(View.OnClickListener {
            if (isValidCredentials(username.text.toString(), password.text.toString())) {
                makeLoginRequest(username, password)
            } else {
                password.text.clear()
            }
        })

        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return !username.isEmpty() && !password.isEmpty()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun parseTokenFromJson(json: String?): String? {
        try {
            val jsonObject = JSONObject(json)
            return jsonObject.getString("accessToken")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    private fun saveTokenToSharedPreferences(token: String?) {
        val sharedPreferences = getSharedPreferences("LOGIN_TOKEN", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token_key", token)
        editor.apply()
        Log.d("SharedPreferences", "Saved Token: $token")
    }

    private fun makeLoginRequest(email: TextView, password: TextView) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("username", email.text.toString())
        jsonObject.addProperty("password", password.text.toString())

        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaType())

        val service = RetrofitClient.getClient().create(ApiService::class.java)
        val call: Call<ResponseBody> = service.login(requestBody)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("LoginRequest", "Response Code: ${response.code()}")
                Log.d("LoginRequest", "Response Headers: ${response.headers()}")

                if (response.isSuccessful) {
                    val responseBody: ResponseBody? = response.body()
                    val tokenJson = responseBody?.string()

                    Log.d("LoginRequest", "Response Body: $tokenJson")

                    val token = parseTokenFromJson(tokenJson)
                    println("and the fucking token iiiis: $token")
                    saveTokenToSharedPreferences(token)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("LoginRequest", "Error Body: ${response.errorBody()?.string()}")
                    System.out.println("failed")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("LoginRequest", "Request Failed: ${t.message}")
                t.printStackTrace()
            }
        })
    }
}
