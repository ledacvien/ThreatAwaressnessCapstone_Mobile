package com.example.threatawarenessmobile

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder()
    .baseUrl("https://threatawareness.pythonanywhere.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val dataService = retrofit.create(ApiService::class.java)
interface ApiService {
    @GET("data")
    fun getWarning(): Call<WarningResponse>
}