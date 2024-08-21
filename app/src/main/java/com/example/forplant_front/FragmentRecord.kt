package com.example.forplant_front

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.FragmentRecordBinding
import com.example.forplant_front.databinding.ItemPlantBinding
import com.example.forplant_front.databinding.RecordBottomsheetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentRecord: Fragment() {
    private lateinit var binding: FragmentRecordBinding
    private var modal: RecordBottomSheet? = null  // BottomSheetDialogFragment
    private lateinit var adapter: RecordRVAdapter
    private lateinit var userPreferences: SharedPreferences
    private var plantList = mutableListOf<RetrofitClient2.Plantinfo>() // Plant 리스트

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

        userPreferences = MyApplication.getUser()
        val token = userPreferences.getString("jwt", "") ?: ""

        binding.recordPlusBtn.setOnClickListener {
            // 바텀시트 클릭 이벤트 처리
            showBottomSheet()
        }

        // RecyclerView 설정
        setupRecyclerView()

        if (token.isNotEmpty()) {
            fetchPlantList(token)
        }

        return binding.root
    }

    private fun showBottomSheet() {
        modal = RecordBottomSheet()
        modal?.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal?.show(parentFragmentManager, RecordBottomSheet.TAG)
    }

    private fun setupRecyclerView() {
        // RecyclerView의 레이아웃 매니저 설정
        binding.recordPlantlistRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // RecyclerView 초기화
        adapter = RecordRVAdapter(plantList)
        binding.recordPlantlistRv.adapter = adapter
        //adapter.enableSelectionMode()
    }

    private fun fetchPlantList(token: String) {
        val call = RetrofitObject.getRetrofitService.getPlantList(token)
        call.enqueue(object : Callback<RetrofitClient2.ResponsePlantlist> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponsePlantlist>,
                response: Response<RetrofitClient2.ResponsePlantlist>
            ) {
                if (response.isSuccessful) {
                    val plantResponse = response.body()
                    if (plantResponse != null && plantResponse.isSuccess) {
                        plantList.clear()
                        plantList.addAll(plantResponse.result)
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d("FragmentRecord", "API 실패: ${plantResponse?.message}")
                    }
                } else {
                    Log.d("FragmentRecord", "응답 실패: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(
                call: Call<RetrofitClient2.ResponsePlantlist>,
                t: Throwable
            ) {
                Log.d("FragmentRecord", "API 호출 실패: ${t.message}")
            }
        })
    }
}
