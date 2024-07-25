package com.example.forplant_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityFindtestChoice1Binding

class findtest_choice1 : AppCompatActivity() {
    private lateinit var binding: ActivityFindtestChoice1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFindtestChoice1Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.findtestATop.setOnClickListener {
            val intent = Intent(this,findtest_wait::class.java)
            startActivity(intent)
        }
    }
}