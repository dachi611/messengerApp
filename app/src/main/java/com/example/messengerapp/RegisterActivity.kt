package com.example.messengerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
    }

    override fun onResume() {
        super.onResume()

        val registerName: TextView = findViewById(R.id.registerName)
        val registerLastName: TextView =findViewById(R.id.registerLastname)
        val registerBirthDate: TextView =findViewById(R.id.registerBirthDate)
        val registerEmail : TextView =  findViewById(R.id.registerEmail)
        val registerPassword : TextView =findViewById(R.id.registerPassword)
        val submitBtn : Button = findViewById(R.id.submitRegistration)

        submitBtn.setOnClickListener {
            submit(registerName, registerLastName, registerPassword)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    fun submit(registerName: TextView, registerLastName: TextView, registerPassword: TextView) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("username", "${registerName.text} ${registerLastName.text}")
        jsonObject.addProperty("password", "${registerPassword.text}")

        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaType())

        val service = RetrofitClient.getClient().create(ApiService::class.java)
        val call: Call<ResponseBody> = service.postData(requestBody)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody: ResponseBody? = response.body()
                    val jsonString: String? = responseBody?.string()
                    println("Received JSON data: $jsonString")
                } else {
                    // Handle error response
                    // You can check response.errorBody() for more details
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Handle failure
                t.printStackTrace()
            }
        })
    }

}