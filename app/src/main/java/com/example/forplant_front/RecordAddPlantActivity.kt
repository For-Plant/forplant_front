package com.example.forplant_front

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityRecordAddplantBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class RecordAddPlantActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRecordAddplantBinding
    private var selectedImageUri: Uri? = null
    private lateinit var adapter: RecordRVAdapter
    private lateinit var userPreferences: SharedPreferences

    val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            // 권한 부여
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            this.contentResolver.takePersistableUriPermission(uri, flag)
            // 이미지뷰에 선택한 이미지 설정
            changePlantImage(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordAddplantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        uploadedPhoto()
        addPlant()
    }

    private fun goBack() {
        binding.addplantBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun uploadedPhoto() {
        binding.addplantUploadCl.setOnClickListener {
            viewImgPicker()
        }
    }

    private fun changePlantImage(uri: Uri) {
        selectedImageUri = uri
        // 선택한 이미지를 프로필 이미지뷰에 설정
        binding.addplantPhotoIv.visibility = View.VISIBLE
        binding.addplantUploadCl.visibility = View.GONE
        binding.addplantPhotoIv.setImageURI(uri)
    }

    private fun viewImgPicker() {
        binding.addplantUploadCl.setOnClickListener {
            //커스텀 갤러리 화면
            // Launch the photo picker and let the user choose images and videos.
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    private fun addPlant() {
        binding.addplantAddBtn.setOnClickListener {
            val name = binding.addplantNameEt.text.toString()
            val nickname = binding.addplantNicknameEt.text.toString()
            val createdAt = binding.addplantDateEt.text.toString()

            if (name.isNotEmpty() && nickname.isNotEmpty() && createdAt.isNotEmpty()) {
                userPreferences = MyApplication.getUser()
                val token = userPreferences.getString("jwt", "") ?: ""

                val nameRequestBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
                val nicknameRequestBody = nickname.toRequestBody("text/plain".toMediaTypeOrNull())
                val createdAtRequestBody = createdAt.toRequestBody("text/plain".toMediaTypeOrNull())
                // 이미지가 선택된 경우에만 파일로 변환하여 전송, 그렇지 않으면 null을 보냄
                val imagePart = selectedImageUri?.let {
                    val imageFile = uriToFile(it)
                    val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("plant_img", imageFile.name, imageRequestBody)
                }

                val call = RetrofitObject.getRetrofitService.addPlant(token, nameRequestBody, nicknameRequestBody, createdAtRequestBody, imagePart)
                call.enqueue(object : Callback<RetrofitClient2.ResponseAddPlant> {
                    override fun onResponse(call: Call<RetrofitClient2.ResponseAddPlant>, response: Response<RetrofitClient2.ResponseAddPlant>) {
                        Log.d("RecordAddPlantActivity", "Response Body: ${response.body().toString()}")
                        Log.d("RecordAddPlantActivity", response.toString())
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            Log.d("RecordAddPlantActivity", "responseBody: $responseBody")
                            val plantId = responseBody?.result?.plantId
                            if (responseBody != null && responseBody.isSuccess) {
                                val resultIntent = Intent().apply {
                                    putExtra("newPlantName", name)
                                    putExtra("newPlantNickname", nickname)
                                    putExtra("newPlantId", plantId)
                                }
                                setResult(RESULT_OK, resultIntent)
                                Toast.makeText(this@RecordAddPlantActivity, "식물이 추가되었습니다", Toast.LENGTH_SHORT).show()
                                Log.d("RecordAddPlantActivity", "식물 추가 성공. Plant ID: ${responseBody.result.plantId}")
                                finish()
                            } else {
                                Log.e("RecordAddPlantActivity", "식물 추가 실패: ${responseBody?.message}")
                            }
                        } else {
                            Log.e("RecordAddPlantActivity", "응답 실패: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<RetrofitClient2.ResponseAddPlant>, t: Throwable) {
                        Log.e("RecordAddPlantActivity", "API 호출 실패: ${t.message}")
                    }
                })
            } else {
                Toast.makeText(this, "이름, 별명, 날짜를 모두 적어주세요.", Toast.LENGTH_SHORT).show()
                Log.d("RecordAddPlantActivity", "모든 필드를 채워주세요.")
            }
        }
    }

    private fun uriToFile(uri: Uri): File {
        val file = File(cacheDir, contentResolver.query(uri, null, null, null, null)
            ?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                cursor.getString(nameIndex)
            } ?: "temp_image")

        contentResolver.openInputStream(uri).use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
        }
        return file
    }
}