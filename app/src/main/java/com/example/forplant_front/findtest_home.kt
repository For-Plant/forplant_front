package com.example.forplant_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forplant_front.databinding.ActivityFindtestHomeBinding

class findtest_home : AppCompatActivity() {
    private lateinit var binding: ActivityFindtestHomeBinding
    private var question = 1
    private val answersMap = mutableMapOf(
        "EI1" to 0, "EI2" to 0, "EI3" to 0,
        "SN1" to 0, "SN2" to 0, "SN3" to 0,
        "TF1" to 0, "TF2" to 0, "TF3" to 0,
        "JP1" to 0, "JP2" to 0, "JP3" to 0
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFindtestHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.findtestNextBtn.setOnClickListener {
            val intent = Intent(this,findtest_choice1::class.java)
            intent.putExtra("question", question)
            intent.putExtra("answersMap", HashMap(answersMap))
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }
}