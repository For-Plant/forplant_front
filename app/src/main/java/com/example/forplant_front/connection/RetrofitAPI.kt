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

    @GET("/mypage/alive-plants")
    fun getmypageplant(@Header("x-access-token") token: String): Call<RetrofitClient2.Responsegetmypageplant>

    @GET("/mypage/dead-plants")
    fun getmypageripplant(@Header("x-access-token") token: String): Call<RetrofitClient2.Responsegetmypageripplant>

    @GET("/mypage/dead-plant-details")
    fun getripplantrecord(@Header("x-access-token") token: String,
                          @Query("plant_nickname") member_id: String): Call<RetrofitClient2.Responsegetripplantrecord>

    @GET("/mypage/record-dates")
    fun getripplantdate(@Header("x-access-token") token: String,
                          @Query("plant_nickname") member_id: String): Call<RetrofitClient2.Responsegetripplantdate>

    @GET("/mypage/record-content")
    fun getripplantdaterecord(@Header("x-access-token") token: String,
                          @Query("plant_nickname") plantNickname: String,
                          @Query("date") date: String): Call<RetrofitClient2.Responsegetripplantdaterecord>

    @GET("/mypage/profile")
    fun getprofile(@Header("x-access-token") token: String): Call<RetrofitClient2.Responsegetprofile>

    @Multipart
    @PUT("/mypage/profile")
    fun modifyprofile(@Header("x-access-token") token: String,
                      @Part("nickname") nickname: RequestBody?,
                      @Part image: MultipartBody.Part?,
                      @Part("password") password: RequestBody): Call<RetrofitClient2.Responsemodifyprofile>

    @GET("/record/plant-list")
    fun plantlist(): Call<RetrofitClient2.ResponsePlantlist>

    @GET("/record/record-list?plant_nickname={식물별명}")
    fun writeplantrecord(): Call<RetrofitClient2.ResponseWriteplant>
  
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