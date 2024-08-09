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
        val code: Int,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String,
    )
}