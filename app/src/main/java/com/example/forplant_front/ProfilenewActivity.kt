package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityProfilenewBinding


class ProfilenewActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfilenewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilenewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, SplashwelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}
