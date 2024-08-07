package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivityMypageSecessionBinding

class MypageSeccsionActivity : AppCompatActivity() {
    lateinit var binding: ActivityMypageSecessionBinding
    private var isChecked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSecessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView2.setOnClickListener {
            finish()
        }
        // 이미지 뷰 클릭 이벤트 리스너 설정
        binding.imageView25.setOnClickListener {
            // 토글 상태 변경
            isChecked = !isChecked

            // 이미지 변경
            if (isChecked) {
                binding.imageView25.setImageResource(R.drawable.btn_checkbox_checked)
                binding.button3.setBackgroundResource(R.drawable.green_rectangle56)
            } else {
                binding.imageView25.setImageResource(R.drawable.btn_checkbox)
                binding.button3.setBackgroundResource(R.drawable.gray_rectangle)
            }
        }
        binding.button3.setOnClickListener {
            finish()
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }
}