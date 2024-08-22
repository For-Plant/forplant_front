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

    // Record 1
    @GET("/record/plant-list")
    fun getPlantList(
        @Header("x-access-token") token: String
    ): Call<RetrofitClient2.ResponsePlantlist>

    // Record 2
    @GET("/record/record-list")
    fun getPlantRecords(
        @Header("x-access-token") token: String,
        @Query("plant_nickname") plantNickname: String
    ): Call<RetrofitClient2.ResponseWriteplant>

    // Record 3
    @POST("/record/write-record")
    fun writePlantRecord(
        @Header("x-access-token") token: String,
        @Query("plant_nickname") plantNickname: String,
        @Body content: RetrofitClient2.RequestWriteRecord
    ): Call<RetrofitClient2.ResponseWriteRecord>

    // Record 4
    @GET("/record/get-content")
    fun getPlantRecordContent(
        @Header("x-access-token") token: String,
        @Query("plant_nickname") plantNickname: String,
        @Query("date") date: String
    ): Call<RetrofitClient2.ResponseCheckRecord>

    // Record 5
    @Multipart
    @POST("/record/add-plant")
    fun addPlant(
        @Header("x-access-token") token: String,
        @Part("name") name: RequestBody,
        @Part("nickname") nickname: RequestBody,
        @Part("created-at") createdAt: RequestBody,
        @Part plantImg: MultipartBody.Part? // 이미지 파트는 null 가능하도록 설정
    ): Call<RetrofitClient2.ResponseAddPlant>

    // Record 6


    // Record 7


    // Record 8
    @POST("/record/dead-plant")
    fun reportDeadPlant(
        @Header("x-access-token") token: String,
        @Query("plant_nickname") plantNickname: String,
        @Body requestBody: RetrofitClient2.RequestRipplant
    ): Call<RetrofitClient2.ResponseRipplant>

    // Record 9


    // Record 10


    // Record 11
    @Multipart
    @DELETE("record/delete-record")
    fun deletePlantRecord(
        @Header("x-access-token") token: String,
        @Part("name") name: RequestBody,
        @Part("nickname") nickname: RequestBody,
        @Part("created_at") createdAt: RequestBody,
        @Part plantImg: MultipartBody.Part,
        @Query("plant_nickname") plantNickname: String,
        @Query("date") date: String
    ): Call<RetrofitClient2.ResponseDeleteRecord>

    // Chat 1
    @POST("/chat/start")
    fun startChat(
        @Header("x-access-token") token: String
    ): Call<RetrofitClient2.ResponseStartChat>

    // Chat 2
    @POST("/chat/send")
    fun sendMessage(
        @Header("x-access-token") token: String,
        @Body messageRequest: RetrofitClient2.RequestMessage
    ): Call<RetrofitClient2.ResponseMessage>

    // Chat 3
    @GET("/chat/list")
    fun getChatList(
        @Header("x-access-token") token: String
    ): Call<RetrofitClient2.ResponseChatList>

    // Chat 4
    @GET("/chat/message")
    fun getChatMessages(
        @Header("x-access-token") token: String,
        @Query("room_title") roomTitle: String
    ): Call<RetrofitClient2.ResponseChatMessages>
  
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

    @Multipart
    @POST("/predict/ai-camera")
    fun uploadImage(
        @Header("x-access-token") token: String,
        @Part image: MultipartBody.Part
    ): Call<RetrofitClient2.AiCameraResponse>

}