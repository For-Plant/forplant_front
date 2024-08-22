package com.example.forplant_front

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityMypageRipBinding
import com.example.forplant_front.databinding.RipbottomsheetdialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RipBottomSheet : AppCompatActivity() {
    private lateinit var adapter: RipRecordAdapter
    private lateinit var binding: RipbottomsheetdialogBinding
    private lateinit var user: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var token: String
    private lateinit var plantNickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RipbottomsheetdialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용자 SharedPreferences 초기화
        user = MyApplication.getUser()
        token = user.getString("jwt", "").toString()

        // Intent에서 plant_nickname 값을 받아오기
        plantNickname = intent.getStringExtra("plant_nickname").toString()

        binding.imageView2.setOnClickListener {
            finish()
        }
//        val itemList = listOf("아이템 1", "아이템 2", "아이템 3","아이템 4")
//        setupRecyclerView(itemList)

        loadData()
    }

    private fun loadData() {
        val call = RetrofitObject.getRetrofitService.getripplantdate("$token",plantNickname)
        call.enqueue(object : Callback<RetrofitClient2.Responsegetripplantdate> {
            override fun onResponse(call: Call<RetrofitClient2.Responsegetripplantdate>, response: Response<RetrofitClient2.Responsegetripplantdate>) {
                if (response.isSuccessful) {

                    // response.body()가 null일 수 있으므로 null 체크
                    val responseBody = response.body()
                    if (responseBody != null) {
                        // profileData가 null일 수 있으므로 null 체크
                        val profileData = responseBody.result?.recordDates

                        if (profileData.isNullOrEmpty()) {
                            // 식물 데이터가 없으면 'textView66'을 보이도록 처리
                            binding.textView66.visibility = View.VISIBLE
                            binding.poseRv.visibility = View.GONE
                        } else {
                            // 식물 데이터가 있으면 'textView66'을 숨기고 'poseRv'를 보이도록 처리
                            binding.textView66.visibility = View.GONE
                            binding.poseRv.visibility = View.VISIBLE
                            setupRecyclerView(profileData)
                        }
                    } else {
                        // response.body()가 null인 경우 처리
                        Toast.makeText(
                            this@RipBottomSheet,
                            "Response body is null",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // API 호출이 실패한 경우 처리
                    Toast.makeText(
                        this@RipBottomSheet,
                        response.body()?.message ?: "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<RetrofitClient2.Responsegetripplantdate>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("Retrofit5", errorMessage)
            }
        })
    }

    private fun setupRecyclerView(itemList: List<String>) {
        // RecyclerView의 레이아웃 매니저 설정
        binding.poseRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // RecyclerView의 어댑터 설정
        adapter = RipRecordAdapter(itemList, plantNickname)
        binding.poseRv.adapter = adapter
    }
}