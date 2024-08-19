package com.example.forplant_front

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityCameraPreviewBinding

class camera_preview : AppCompatActivity() {
    private lateinit var binding: ActivityCameraPreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraPreviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val imageUri = intent.getStringExtra("image_uri")
        if (imageUri != null) {
            binding.cameraPreview.setImageURI(Uri.parse(imageUri))
        }

        binding.cameraRetry.setOnClickListener {
            finish()  // 이전 액티비티로 돌아감
        }

        binding.cameraNextChoice.setOnClickListener {
            val intent = Intent(this,camera_result::class.java)
            startActivity(intent)
        }
        binding.back.setOnClickListener{
            finish()
        }
    }
}