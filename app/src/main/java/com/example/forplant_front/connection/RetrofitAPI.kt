package com.example.forplant_front.connection

import retrofit2.Call
import retrofit2.http.*

interface RetrofitAPI {
    @POST("/user/login")
    fun login(@Body request: RetrofitClient2.Requestlogin): Call<RetrofitClient2.Responselogin>

    @POST("/user/find-id")
    fun findid(@Body request: RetrofitClient2.Requestfindid): Call<RetrofitClient2.Responsefindid>

    @PUT("/user/change-pw")
    fun changepw(@Body request: RetrofitClient2.Requestchangepw): Call<RetrofitClient2.Responsechangepw>

    @GET("/home/homescreen")
    fun getHomeScreen(
        @Header("x-access-token") token: String
    ): Call<RetrofitClient2.HomeResponse>

    @GET("/home/question")
    fun getQuestion(
        @Header("x-access-token") token: String,
        @Query("num") num: Int
    ): Call<RetrofitClient2.findtestchoiceResponse>

    @POST("/home/soulmate-result")
    fun getSoulmateResult(
        @Header("x-access-token") token: String,
        @Body answers: Map<String, Int>
    ): Call<RetrofitClient2.findtestresultResponse>
}