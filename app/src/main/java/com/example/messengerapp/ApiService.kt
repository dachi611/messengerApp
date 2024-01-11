package com.example.messengerapp

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("fact")
    fun getUserData(): Call<ResponseBody>
    @POST("register")
    fun postData(@Body requestBody: RequestBody): Call<ResponseBody>
    @POST("login")
    fun login(@Body credentials: RequestBody): Call<ResponseBody>
}