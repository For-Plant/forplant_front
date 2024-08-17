package com.example.forplant_front.connection

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface RetrofitAPI {
    @POST("/user/login")
    fun login(@Body request: RetrofitClient2.Requestlogin): Call<RetrofitClient2.Responselogin>

    @POST("/user/find-id")
    fun findid(@Body request: RetrofitClient2.Requestfindid): Call<RetrofitClient2.Responsefindid>

    @PUT("/user/change-pw")
    fun changepw(@Body request: RetrofitClient2.Requestchangepw): Call<RetrofitClient2.Responsechangepw>

    @GET("/record/plant-list")
    fun plantlist(): Call<RetrofitClient2.ResponsePlantlist>

    @GET("/record/record-list?plant_nickname={식물별명}")
    fun writeplantrecord(): Call<RetrofitClient2.ResponseWriteplant>
}