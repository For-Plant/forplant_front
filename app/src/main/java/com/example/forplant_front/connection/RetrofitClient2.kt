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
        val member_id: String
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

    data class EmptyResult(val dummy: String = "")

    data class Responsegetid(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: GetidResult,
    )
    data class GetidResult(
        @SerializedName("user_id")
        val user_id: Int,
        @SerializedName("member_id")
        val member_id: String
    )

    data class Responsesignup(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: Int,
    )

    data class Responsegetmypage(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: MypageResult,
    )
    data class MypageResult(
        @SerializedName("user")
        val user: User,
        @SerializedName("representPlant")
        val representPlant: RepresentPlant,
        @SerializedName("alivePlantsCount")
        val alivePlantsCount: Int,
        @SerializedName("alivePlants")
        val alivePlants: List<Plant>,
        @SerializedName("deadPlantsCount")
        val deadPlantsCount: Int,
        @SerializedName("deadPlants")
        val deadPlants: List<DeadPlant>
    )

    data class User(
        @SerializedName("member_id")
        val memberId: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("profile_img")
        val profileImg: String,
        @SerializedName("password")
        val password: String
    )

    data class RepresentPlant(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("created_at")
        val createdAt: String
    )

    data class Plant(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String
    )

    data class DeadPlant(
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("plant_created_at")
        val plantCreatedAt: String,
        @SerializedName("dead_created_at")
        val deadCreatedAt: String
    )
}