package com.example.forplant_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityCameraPageBinding

class camera_page : AppCompatActivity() {
    private lateinit var binding: ActivityCameraPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cameraBtn.setOnClickListener {
            val intent = Intent(this, camera_preview::class.java)
            startActivity(intent)
        }
    }
}