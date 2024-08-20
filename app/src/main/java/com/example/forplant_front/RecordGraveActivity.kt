package com.example.forplant_front

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivityRecordGraveBinding

class RecordGraveActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordGraveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordGraveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.graveBackBtn.setOnClickListener {
            finish()
        }

        // 식물 이름 받아오기

    }
}