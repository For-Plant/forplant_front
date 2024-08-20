package com.example.forplant_front

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityRecordPlantBinding
import com.example.forplant_front.databinding.ActivityRipListOnedayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordPlantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordPlantBinding
    private lateinit var adapter: RecordPlantRVAdapter
    lateinit var plantNickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 전달된 데이터 받기
        plantNickname = intent.getStringExtra("PLANT_NICKNAME") ?: ""

        // 받은 데이터를 UI에 반영
        if (plantNickname != null) {
            binding.recordPlantnameTv.text = plantNickname
        }

        goBack()

        binding.recordPlusBtn.setOnClickListener {
            showBottomSheet()
        }

        // 리사이클러뷰 초기 설정
        adapter = RecordPlantRVAdapter(mutableListOf()) // 빈 리스트로 초기화
        binding.recordRecordRv.layoutManager = LinearLayoutManager(this)
        binding.recordRecordRv.adapter = adapter

//        //아이템 추가
//        val itemList = listOf("2024.08.17", "2024.08.07")
//        setupRecyclerView(itemList)

        val token = MyApplication.getUser().getString("jwt", "") ?: ""
        fetchPlantRecords(token, plantNickname)
    }

    private fun goBack() {
        binding.recordBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun showBottomSheet() {
        val modal = RecordPlantBottomSheet()
        modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal.show(supportFragmentManager, RecordPlantBottomSheet.TAG)
    }

    private fun showPlantImage(plantImageUrl: String) {
        //등록한 사진이 있다면 등록한 사진 보여주기, 없다면 기본 이미지 사용
        Glide.with(this)
            .load(plantImageUrl ?: R.drawable.iv_defplant) // 이미지 URL이 null 또는 비어있다면 기본 이미지 사용
            .error(R.drawable.iv_defplant) // 이미지 로드 실패 시 기본 이미지 사용
            .into(binding.recordPlantIv)
    }

    private fun setupRecyclerView(recordlist: List<String>) {
        adapter.updateData(recordlist)
    }

    private fun fetchPlantRecords(token: String, plantNickname: String) {
        val call = RetrofitObject.getRetrofitService.getPlantRecords(token, plantNickname)
        call.enqueue(object : Callback<RetrofitClient2.ResponseWriteplant> {
            override fun onResponse(call: Call<RetrofitClient2.ResponseWriteplant>, response: Response<RetrofitClient2.ResponseWriteplant>) {
                if (response.isSuccessful) {
                    val plantData = response.body()?.result
                    Log.d("RecordPlantActivity", "plantData: $plantData")
                    if (plantData != null) {  // 아이템이 하나라도 있을 경우
                        binding.recordNolistTv.visibility = View.GONE
                        binding.recordRecordRv.visibility = View.VISIBLE

                        showPlantImage(plantData.plantImage)
                        setupRecyclerView(plantData.recordDates)
                        Log.d("RecordPlantActivity", "받아오기 성공")
                    }
                } else {
                    Log.d("RecordPlantActivity", "Response not successful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseWriteplant>, t: Throwable) {
                Log.d("RecordPlantActivity", "API Call failed: ${t.message}")
            }
        })
    }
}