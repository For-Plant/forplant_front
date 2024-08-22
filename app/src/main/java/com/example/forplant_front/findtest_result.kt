package com.example.forplant_front

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityFindtestResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class findtest_result : AppCompatActivity() {
    private lateinit var binding: ActivityFindtestResultBinding
    private lateinit var answersMap: HashMap<String, Int>
    private lateinit var user: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFindtestResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        answersMap = intent.getSerializableExtra("answersMap") as? HashMap<String, Int> ?: HashMap()

        Log.d("findtest_result", "Received answersMap: $answersMap")

        // SharedPreferences에서 JWT 토큰 가져오기
        user = getSharedPreferences("user", Context.MODE_PRIVATE)
        val token = user.getString("jwt", "") ?: ""

        // API 호출
        fetchSoulmateResult(token, answersMap)

        binding.findtestAgainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            val newIntent = Intent(this, findtest_home::class.java)
            startActivity(newIntent)
        }
        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            val newIntent = Intent(this, findtest_home::class.java)
            startActivity(newIntent)
        }
    }
    private fun fetchSoulmateResult(token: String, answers: HashMap<String, Int>) {
        val call = RetrofitObject.getRetrofitService.getSoulmateResult(token, answers)
        call.enqueue(object : Callback<RetrofitClient2.findtestresultResponse> {
            override fun onResponse(call: Call<RetrofitClient2.findtestresultResponse>, response: Response<RetrofitClient2.findtestresultResponse>) {
                if (response.isSuccessful) {
                    val soulmateResponse = response.body()
                    if (soulmateResponse != null && soulmateResponse.isSuccess) {
                        val result = soulmateResponse.result.firstOrNull()
                        binding.findtestName.text = result?.plantname
                        binding.findtestNameMore.text = "'${result?.plantname}'에 대해 아래에서 살펴봅시다."
                        binding.findtestType.text = result?.plant_feature
                        binding.findtestBackground.text = result?.plant_environment
                        binding.findtestGrow.text = result?.how_to_grow
                        binding.findtestTmi.text = result?.plant_tmi
                        Glide.with(this@findtest_result)
                            .load(result?.plant_img)
                            .into(binding.findtestResultImg)

                    } else {
                        Log.d("findtest_result", "API 호출 성공하지만 응답 에러: ${soulmateResponse?.message}")
                    }
                } else {
                    Log.d("findtest_result", "API 호출 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.findtestresultResponse>, t: Throwable) {
                Log.d("findtest_result", "API 호출 실패: ${t.message}")
            }
        })
    }
}