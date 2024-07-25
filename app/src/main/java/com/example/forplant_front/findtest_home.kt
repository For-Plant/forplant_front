package com.example.forplant_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityFindtestHomeBinding

class findtest_home : AppCompatActivity() {
    private lateinit var binding: ActivityFindtestHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFindtestHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.findtestNextBtn.setOnClickListener {
            val intent = Intent(this,findtest_choice1::class.java)
            startActivity(intent)
        }
    }
}