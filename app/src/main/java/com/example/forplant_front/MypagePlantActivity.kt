package com.example.forplant_front

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityMypageListBinding
import com.example.forplant_front.databinding.ActivityMypageRipBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MypagePlantActivity : AppCompatActivity() {
    lateinit var binding: ActivityMypageListBinding
    private lateinit var adapter: PlantListsAdapter
    private lateinit var user: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용자 SharedPreferences 초기화
        user = MyApplication.getUser()
        token = user.getString("jwt", "").toString()

        binding.imageView2.setOnClickListener {
            finish()
        }

        binding.imageView29.setOnClickListener {
            val intent = Intent(this, RecordAddPlantActivity::class.java)
            startActivity(intent)
        }

        loadData()

    }

    private fun loadData() {
        val call = RetrofitObject.getRetrofitService.getmypageplant("$token")
        call.enqueue(object : Callback<RetrofitClient2.Responsegetmypageplant> {
            override fun onResponse(call: Call<RetrofitClient2.Responsegetmypageplant>, response: Response<RetrofitClient2.Responsegetmypageplant>) {
                if (response.isSuccessful) {
                    Log.d("Retrofit5", response.toString())
                    // response.body()를 통해 Responsegetmypage 객체에 접근
                    val profileData = response.body()?.result
                    // 식물 데이터가 없으면 'textViewnot'을 보이도록 처리
                    if (profileData.isNullOrEmpty()) {
                        binding.textView69.visibility = View.VISIBLE
                        binding.imageView29.visibility = View.VISIBLE
                        binding.poseRv.visibility = View.GONE
                    } else {
                        binding.textView69.visibility = View.GONE
                        binding.imageView29.visibility = View.GONE
                        binding.poseRv.visibility = View.VISIBLE
                        setupRecyclerView(profileData)
                    }
                } else {
                    Toast.makeText(
                        this@MypagePlantActivity,
                        response.body()?.message ?: "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<RetrofitClient2.Responsegetmypageplant>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("Retrofit5", errorMessage)
            }
        })
    }

    private fun setupRecyclerView(plantData: List<RetrofitClient2.Plant>) {
        // RecyclerView의 레이아웃 매니저 설정
        binding.poseRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // RecyclerView의 어댑터 설정
        adapter = PlantListsAdapter(plantData)
        binding.poseRv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // 프로필 이미지 다시 불러오기
        loadData()
    }
}
