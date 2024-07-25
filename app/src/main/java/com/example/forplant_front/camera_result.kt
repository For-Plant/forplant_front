package com.example.forplant_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityCameraResultBinding

class camera_result : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}