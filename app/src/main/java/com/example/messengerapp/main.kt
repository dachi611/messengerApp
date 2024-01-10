package com.example.messengerapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun main(){
    makeRequest()
}
val service = RetrofitClient.getClient().create(ApiService::class.java)
val call: Call<ResponseBody> = service.getUserData()
fun makeRequest(){
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                val responseBody: ResponseBody? = response.body()
                val jsonString: String? = responseBody?.string()

                // Print the raw JSON data to the console
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