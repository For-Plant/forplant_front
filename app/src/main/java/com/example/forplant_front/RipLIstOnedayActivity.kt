package com.example.forplant_front

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityRipListOnedayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RipLIstOnedayActivity : AppCompatActivity() {
    lateinit var binding: ActivityRipListOnedayBinding
    private lateinit var user: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var token: String
    private lateinit var plantNickname: String
    private lateinit var plantdate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRipListOnedayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용자 SharedPreferences 초기화
        user = MyApplication.getUser()
        token = user.getString("jwt", "").toString()

        // Intent에서 plant_nickname 값을 받아오기
        plantNickname = intent.getStringExtra("plant_nickname").toString()
        plantdate = intent.getStringExtra("plant_date").toString()

        binding.textView64.text = plantdate
        binding.textView63.text = plantNickname

        binding.imageView2.setOnClickListener {
            finish()
        }

        binding.button.setOnClickListener {
            finish()
        }

        loadData()

    }

    private fun loadData() {
        val call = RetrofitObject.getRetrofitService.getripplantdaterecord("$token",plantNickname, plantdate)
        call.enqueue(object : Callback<RetrofitClient2.Responsegetripplantdaterecord> {
            override fun onResponse(call: Call<RetrofitClient2.Responsegetripplantdaterecord>, response: Response<RetrofitClient2.Responsegetripplantdaterecord>) {
                if (response.isSuccessful) {
                    Log.d("Retrofit5", response.toString())
                    // response.body()를 통해 Responsegetmypage 객체에 접근
                    val profileData = response.body()?.result
                    if (profileData != null) {
                        val content = profileData.content
                        binding.textView65.text = content

                    } else {
                        // profileData가 null일 경우 처리
                        Toast.makeText(
                            this@RipLIstOnedayActivity,
                            "Failed to load data: result is null",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@RipLIstOnedayActivity,
                        response.body()?.message ?: "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<RetrofitClient2.Responsegetripplantdaterecord>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("Retrofit5", errorMessage)
            }
        })
    }
}
