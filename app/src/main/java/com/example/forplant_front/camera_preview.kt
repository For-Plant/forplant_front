package com.example.forplant_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityCameraPreviewBinding

class camera_preview : AppCompatActivity() {
    private lateinit var binding: ActivityCameraPreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraPreviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cameraNextChoice.setOnClickListener {
            val intent = Intent(this,camera_result::class.java)
            startActivity(intent)
        }
    }
}