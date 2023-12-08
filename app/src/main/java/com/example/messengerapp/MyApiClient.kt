package com.example.messengerapp

import okhttp3.OkHttpClient
import okhttp3.Request

class MyApiClient {
    val client = OkHttpClient()

    fun run(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            return response.body?.string() ?: "Empty response"
        }
    }
}