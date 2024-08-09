package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.ActivityRipListBinding


class PlantRecordLIstRipActivity : AppCompatActivity() {
    lateinit var binding: ActivityRipListBinding
    private lateinit var adapter: RipRecordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRipListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, RipBottomSheet::class.java)
            startActivity(intent)
        }
    }
}