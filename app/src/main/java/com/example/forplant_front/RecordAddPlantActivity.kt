package com.example.forplant_front

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivityRecordAddplantBinding

class RecordAddPlantActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRecordAddplantBinding
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
        //업로드 된 사진이 잇다면
//        binding.addplantUploadCl.visibility = View.GONE
//        binding.addplantPhotoIv.visibility = View.VISIBLE
        //업로드 된 사진이 없다면
        binding.addplantUploadCl.setOnClickListener {
            viewImgPicker()
        }
    }

    private fun addPlant() {
        binding.addplantAddBtn.setOnClickListener {
            //토스트 메세지로 등록되었다는 것을 띄워주기
            Toast.makeText(this, "식물이 등록되었습니다.", Toast.LENGTH_SHORT).show()
            //백엔드로 데이터 전송

            //창 닫기
            finish()
        }
    }

    private fun changePlantImage(uri: Uri) {
        // 선택한 이미지를 프로필 이미지뷰에 설정
        binding.addplantPhotoIv.setImageURI(uri)
    }

    private fun viewImgPicker() {
        binding.addplantUploadCl.setOnClickListener {
            //커스텀 갤러리 화면
            // Launch the photo picker and let the user choose images and videos.
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }
}