package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.SsplashBinding


class SplashActivity : AppCompatActivity() {
    lateinit var binding: SsplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SsplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Thread를 사용하여 1초 대기 후에 LoginActivity로 이동
        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1000) // 1초 대기
                    val intent = Intent(this@SplashActivity, AgreeFirstActivity::class.java)
                    startActivity(intent)
                    finish() // 현재 액티비티를 종료
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        thread.start()
    }
}