package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityMypageListBinding
import com.example.forplant_front.databinding.ActivityMypageRipBinding


class MypagePlantActivity : AppCompatActivity() {
    lateinit var binding: ActivityMypageListBinding
    private lateinit var adapter: PlantListsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }
        val itemList = listOf("아이템 1", "아이템 2", "아이템 3","아이템 4", "아이템 5", "아이템 6","아이템 7", "아이템 8", "아이템 9","아이템 10", "아이템 11", "아이템 12")
        setupRecyclerView(itemList)
    }

    private fun setupRecyclerView(itemList: List<String>) {
        // RecyclerView의 레이아웃 매니저 설정
        binding.poseRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // RecyclerView의 어댑터 설정
        adapter = PlantListsAdapter(itemList)
        binding.poseRv.adapter = adapter
    }
}
