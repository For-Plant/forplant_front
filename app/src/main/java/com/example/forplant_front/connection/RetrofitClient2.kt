package com.example.forplant_front.connection

import com.google.gson.annotations.SerializedName
import java.io.Serial

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

    data class Responsegetmypageplant(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<Plant>,
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
        @SerializedName("username")
        val username: String,
        @SerializedName("profile_img")
        val profileImg: String
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

    data class Responsegetprofile(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: GetprofileResult,
    )
    data class GetprofileResult(
        @SerializedName("member_id")
        val member_id: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("profile_img")
        val profile_img: String,
        @SerializedName("password")
        val password: String
    )

    data class Responsemodifyprofile(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: ModifyResult,
    )

    data class ModifyResult(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: EmptyResult
    )
    
    // 반려식물 목록

    data class ResponsePlantlist(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<Plantinfo>
    )

    data class Plantinfo(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String
    )

    // 특정한 식물 기록 목록
    data class ResponseWriteplant(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: WritePlantinfo
    )

    data class WritePlantinfo(
        @SerializedName("plantName")
        val plantName: String,
        @SerializedName("plantImage")
        val plantImage: String,
        @SerializedName("recordDates")
        val recordDates: List<String>
    )

    // 식물 일지 작성
    data class RequestWriteRecord(
        @SerializedName("content")
        val content: String
    )

    data class ResponseWriteRecord(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: WritePlantResult
    )

    data class WritePlantResult(
        @SerializedName("record_id")
        val record_id: Int
    )

    // 식물 일지 확인
    data class ResponseCheckRecord(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: CheckRecordResult
    )

    data class CheckRecordResult(
        @SerializedName("content")
        val content: String
    )

    // 식물 추가
    data class RequestAddPlant(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("created-at")
        val created_at: String,
        @SerializedName("plant_img")
        val plant_img: String
    )

    data class ResponseAddPlant(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: AddPlantResult
    )

    data class AddPlantResult(
        @SerializedName("plant_id")
        val plant_id: String
    )

    // 대표 식물 설정
    data class ResponseSetrep(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SetrepResult
    )

    data class SetrepResult(
        @SerializedName("fieldCount")
        val fieldCount: String,
        @SerializedName("affectedRows")
        val affectedRows: String,
        @SerializedName("insertId")
        val insertId: String,
        @SerializedName("info")
        val info: String,
        @SerializedName("serverStatus")
        val serverStatus: String,
        @SerializedName("warningStatus")
        val warningStatus: String,
        @SerializedName("changedRows")
        val changedRows: String
    )

    // 식물 삭제
    data class ResponseDeleteplant(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: DeleteplantResult
    )

    data class DeleteplantResult(
        @SerializedName("fieldCount")
        val fieldCount: String,
        @SerializedName("affectedRows")
        val affectedRows: String,
        @SerializedName("insertId")
        val insertId: String,
        @SerializedName("info")
        val info: String,
        @SerializedName("serverStatus")
        val serverStatus: String,
        @SerializedName("warningStatus")
        val warningStatus: String,
        @SerializedName("changedRows")
        val changedRows: String
    )

    // 식물 부고 처리
    data class RequestRipplant(
        @SerializedName("dead_date")
        val deadDate: String,
        @SerializedName("reason")
        val reason: String,
        @SerializedName("memorial_letter")
        val memorialLetter: String
    )

    data class ResponseRipplant(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: Int
    )

    // 식물 수정 : 기존 사용자 정보 자동 입력을 위함
    data class ResponseEditplantinfo(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: EditplantinfoResult
    )

    data class EditplantinfoResult(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("img")
        val img: String
    )

    // 식물 수정 : 수정한 내용 저장
    data class RequestSaveEditplant(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("plant_img")
        val plant_img: String
    )
    data class ResponseSaveEditplant(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SaveEditplantResult
    )

    data class SaveEditplantResult(
        @SerializedName("fieldCount")
        val fieldCount: String,
        @SerializedName("affectedRows")
        val affectedRows: String,
        @SerializedName("insertId")
        val insertId: String,
        @SerializedName("info")
        val info: String,
        @SerializedName("serverStatus")
        val serverStatus: String,
        @SerializedName("warningStatus")
        val warningStatus: String,
        @SerializedName("changedRows")
        val changedRows: String
    )

    // 식물일지 삭제
    data class RequestDeleteRecord(
        @SerializedName("name")
        val name: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("plant_img")
        val plant_img: String
    )

    data class ResponseDeleteRecord(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: DeleteRecordResult
    )

    data class DeleteRecordResult(
        @SerializedName("deletedRecords")
        val deletedRecords: String
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
}