package com.example.forplant_front.connection

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitAPI {
    @POST("/user/login")
    fun login(@Body request: RetrofitClient2.Requestlogin): Call<RetrofitClient2.Responselogin>

    @POST("/user/find-id")
    fun findid(@Body request: RetrofitClient2.Requestfindid): Call<RetrofitClient2.Responsefindid>

    @PUT("/user/change-pw")
    fun changepw(@Body request: RetrofitClient2.Requestchangepw): Call<RetrofitClient2.Responsechangepw>

    @GET("/user/overlap-id")
    fun getid(@Query("member_id") member_id: String): Call<RetrofitClient2.Responsegetid>
    @Multipart
    @POST("/user/sign-up")
    fun signup(@Part("username") username: RequestBody?,
               @Part("nickname") nickname: RequestBody,
               @Part("member_id") member_id: RequestBody?,
               @Part("password") password: RequestBody,
               @Part("phonenum") phonenum: RequestBody?,
               @Part("marketing_agree") marketing_agree: RequestBody,
               @Part image: MultipartBody.Part?
    ): Call<RetrofitClient2.Responsesignup>

    @GET("/mypage/main")
    fun getmypage(@Header("x-access-token") token: String): Call<RetrofitClient2.Responsegetmypage>
}