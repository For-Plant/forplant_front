package com.example.forplant_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityFindtestResultBinding

class findtest_result : AppCompatActivity() {
    private lateinit var binding: ActivityFindtestResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFindtestResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}