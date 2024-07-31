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

        val bottomSheetFragment = RipBottomSheet()

        binding.imageView2.setOnClickListener {
            finish()
        }

        binding.button.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
//        val itemList = listOf("아이템 1", "아이템 2", "아이템 3", "아이템 4", "아이템 5", "아이템 6")
//        setupRecyclerView(itemList)
    }

//    private fun setupRecyclerView(itemList: List<String>) {
//        // RecyclerView의 레이아웃 매니저 설정
//        binding.poseRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//
//        // RecyclerView의 어댑터 설정
//        adapter = RipRecordAdapter(itemList)
//        binding.poseRv.adapter = adapter
//    }
}