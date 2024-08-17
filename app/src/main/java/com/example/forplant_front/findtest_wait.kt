package com.example.forplant_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class findtest_wait : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findtest_wait)

        // 이전 액티비티에서 넘어온 answersMap 받기
        val answersMap = intent.getSerializableExtra("answersMap") as? HashMap<String, Int>

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,findtest_result::class.java)
            intent.putExtra("answersMap", answersMap)
            startActivity(intent)
            finish()
        },3000)
    }
}