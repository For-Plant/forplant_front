package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivitySignBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding
    private var isChecked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView16.setOnClickListener {
            val intent = Intent(this, AgreeFirstActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.textView19.setOnClickListener {
            val intent = Intent(this, IdfoundActivity::class.java)
            startActivity(intent)
        }
        binding.textView20.setOnClickListener {
            val intent = Intent(this, PasswordfoundActivity::class.java)
            startActivity(intent)
        }
        // 이미지 뷰 클릭 이벤트 리스너 설정
        binding.imageView12.setOnClickListener {
            // 토글 상태 변경
            isChecked = !isChecked
            // 이미지 변경
            if (isChecked) {
                binding.imageView12.setImageResource(R.drawable.btn_checkbox_checked)
            } else {
                binding.imageView12.setImageResource(R.drawable.btn_checkbox)
            }
        }
    }
}
