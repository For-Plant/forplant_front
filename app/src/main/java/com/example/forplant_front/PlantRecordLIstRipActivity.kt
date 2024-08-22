package com.example.forplant_front

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityRipListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlantRecordLIstRipActivity : AppCompatActivity() {
    lateinit var binding: ActivityRipListBinding
    private lateinit var adapter: RipRecordAdapter
    private lateinit var user: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var token: String
    private lateinit var plantNickname: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRipListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용자 SharedPreferences 초기화
        user = MyApplication.getUser()
        token = user.getString("jwt", "").toString()

        // Intent에서 plant_nickname 값을 받아오기
        plantNickname = intent.getStringExtra("plant_nickname").toString()

        binding.imageView2.setOnClickListener {
            finish()
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, RipBottomSheet::class.java)
            intent.putExtra("plant_nickname", plantNickname)  // nickname을 Intent에 추가
            startActivity(intent)
        }

        loadData()
    }

    private fun loadData() {
        val call = RetrofitObject.getRetrofitService.getripplantrecord("$token",plantNickname)
        call.enqueue(object : Callback<RetrofitClient2.Responsegetripplantrecord> {
            override fun onResponse(call: Call<RetrofitClient2.Responsegetripplantrecord>, response: Response<RetrofitClient2.Responsegetripplantrecord>) {
                if (response.isSuccessful) {
                    Log.d("Retrofit5", response.toString())
                    // response.body()를 통해 Responsegetmypage 객체에 접근
                    val profileData = response.body()?.result
                    if (profileData != null) {
                        val nickname = profileData.nickname
                        binding.textView63.text = nickname

                        val plantCreatedAt = profileData.plantCreatedAt
                        binding.textView64.text = plantCreatedAt

                        val deadCreatedAt = profileData.deadCreatedAt
                        binding.textView642.text = deadCreatedAt

                        val letter = profileData.letter
                        binding.textView65.text = letter

                        val img = profileData.img

                        binding.appCompatImageView.clipToOutline=true
                        if (img.isNotEmpty()) {
                            // Glide를 사용하여 이미지 로드
                            Glide.with(this@PlantRecordLIstRipActivity)
                                .load(img)
                                .apply(RequestOptions.bitmapTransform(RoundedCorners(80)))
                                .into(binding.appCompatImageView)
                        } else {
                            // 이미지가 없는 경우 기본 이미지 설정
                            binding.appCompatImageView.setImageResource(R.drawable.icon_noitem)
                        }
                    } else {
                        // profileData가 null일 경우 처리
                        Toast.makeText(
                            this@PlantRecordLIstRipActivity,
                            "Failed to load data: result is null",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@PlantRecordLIstRipActivity,
                        response.body()?.message ?: "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<RetrofitClient2.Responsegetripplantrecord>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("Retrofit5", errorMessage)
            }
        })
    }
}