package com.example.forplant_front

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityCameraResultBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class camera_result : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    private lateinit var user: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // SharedPreferences에서 JWT 토큰 가져오기
        user = getSharedPreferences("user", Context.MODE_PRIVATE)
        val token = user.getString("jwt", "") ?: ""

        val imageUri = intent.getStringExtra("image_uri")

        if (imageUri != null) {
            val imageFile = uriToFile(Uri.parse(imageUri), contentResolver)
            // API 호출
            uploadImage(token, imageFile)
        }
        binding.back.setOnClickListener{
            finish()
        }
    }
    // Uri를 File로 변환하는 함수
    private fun uriToFile(uri: Uri, contentResolver: ContentResolver): File {
        val fileName = "temp_image_file.jpg"
        val tempFile = File.createTempFile(fileName, null, cacheDir)

        contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(tempFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        return tempFile
    }
    // 이미지 파일을 서버로 업로드하는 함수
    private fun uploadImage(token: String, imageFile: File) {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
        val body = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)

        val call = RetrofitObject.getRetrofitService.uploadImage(token, body)
        call.enqueue(object : Callback<RetrofitClient2.AiCameraResponse> {
            override fun onResponse(call: Call<RetrofitClient2.AiCameraResponse>, response: Response<RetrofitClient2.AiCameraResponse>) {
                if (response.isSuccessful) {
                    val soulmateResponse = response.body()
                    if (soulmateResponse != null && soulmateResponse.isSuccess) {
                        val result = soulmateResponse.result

                        // 결과를 처리합니다 (예: UI 업데이트)
                        Log.d("findtest_result", "식물 이름: ${result.plantname}")
                        // 필요에 따라 binding을 통해 결과를 UI에 표시
                        binding.cameraName.text = "'${result.plantname}'으로 보여요!"
                        binding.cameraNameMore.text = "'${result.plantname}'에 대해 아래에서 살펴봅시다."

                        binding.findtestType.text = result.plant_feature
                        binding.findtestBackground.text = result.plant_environment
                        binding.findtestGrow.text = result.how_to_grow
                        binding.findtestTmi.text = result.plant_tmi

                        // 이미지 로드 (Glide 사용)
                        Glide.with(this@camera_result)
                            .load(result.plant_img)
                            .into(binding.cameraPlantImg)

                    } else {
                        Log.d("findtest_result", "API 호출 성공하지만 응답 에러: ${soulmateResponse?.message}")
                    }
                } else {
                    Log.d("findtest_result", "API 호출 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.AiCameraResponse>, t: Throwable) {
                Log.d("findtest_result", "API 아예 호출 실패: ${t.message}")
            }
        })
    }
}