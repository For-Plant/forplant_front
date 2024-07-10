package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityMypageRipBinding


class MypageRipActivity : AppCompatActivity() {
    lateinit var binding: ActivityMypageRipBinding
    private lateinit var adapter: RipAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageRipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }
        val itemList = listOf("아이템 1", "아이템 2", "아이템 3","아이템 4")
        setupRecyclerView(itemList)
    }

    private fun setupRecyclerView(itemList: List<String>) {
        // RecyclerView의 레이아웃 매니저 설정
        binding.poseRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // RecyclerView의 어댑터 설정
        adapter = RipAdapter(itemList)
        binding.poseRv.adapter = adapter
    }
}
