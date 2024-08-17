package com.example.forplant_front.connection

import com.google.gson.annotations.SerializedName

class RetrofitClient2 {
    data class Requestlogin(
        @SerializedName("member_id")
        val member_id: String,
        @SerializedName("password")
        val password: String
    )

    data class Responselogin(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String,
    )

    data class Requestfindid(
        @SerializedName("phonenum")
        val phonenum: String,
        @SerializedName("username")
        val username: String
    )

    data class Responsefindid(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: LoginResult,
    )
    data class LoginResult(
        @SerializedName("member_id")
        val member_id: String,
    )
    data class Requestchangepw(
        @SerializedName("member_id")
        val member_id: String,
        @SerializedName("phonenum")
        val phonenum: String,
        @SerializedName("username")
        val username: String,
        @SerializedName("password")
        val password: String
    )
    data class Responsechangepw(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: EmptyResult // 빈 클래스를 result로 선언
    )

    data class HomeResponse(
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: HomeResult?
    )

    data class HomeResult(
        val plant_nickname: String,
        val plant_img: String,
        val plant_id: Int,
        val date: Int,
        val soulmate_plant: String
    )

    data class findtestchoiceResponse(
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: List<findtestchoiceResult>
    )

    data class findtestchoiceResult(
        val question: String,
        val answer_a: String,
        val answer_b: String
    )

    data class findtestresultResponse(
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: List<findtestresultResult>
    )

    data class findtestresultResult(
        val plantname: String,
        val plant_feature: String,
        val plant_environment: String,
        val how_to_grow: String,
        val plant_tmi: String,
        val plant_img: String
    )

    data class EmptyResult(val dummy: String = "")
}