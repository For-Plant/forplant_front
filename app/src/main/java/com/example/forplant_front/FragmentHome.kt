package com.example.forplant_front

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentHome: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var user: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        user = activity?.getSharedPreferences("user", Context.MODE_PRIVATE) ?: return binding.root
        val token = user.getString("jwt", "") ?: ""

        // API 호출
        fetchHomeScreenData(token)

        binding.homeTestBtn.setOnClickListener {
            val intent = Intent(activity,findtest_home::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    private fun fetchHomeScreenData(token: String) {
        val call = RetrofitObject.getRetrofitService.getHomeScreen(token)
        call.enqueue(object : Callback<RetrofitClient2.HomeResponse> {
            override fun onResponse(call: Call<RetrofitClient2.HomeResponse>, response: Response<RetrofitClient2.HomeResponse>) {
                if (response.isSuccessful) {
                    val plantResponse = response.body()
                    if (plantResponse != null && plantResponse.isSuccess) {
                        Log.d("PlantResponse", "Plant Nickname: ${plantResponse.result?.plant_nickname}")
                        Log.d("PlantResponse", "Plant Image URL: ${plantResponse.result?.plant_img}")
                        Log.d("PlantResponse", "Plant ID: ${plantResponse.result?.plant_id}")
                        Log.d("PlantResponse", "Date: ${plantResponse.result?.date}")
                        Log.d("PlantResponse", "Soulmate Plant: ${plantResponse.result?.soulmate_plant}")

                        val plant_nicname = plantResponse.result?.plant_nickname
                        val plant_img = plantResponse.result?.plant_img
                        val plant_id = plantResponse.result?.plant_id
                        val date = plantResponse.result?.date
                        val soulmate_plant = plantResponse.result?.soulmate_plant

                        if (plant_nicname != null){
                            binding.homeTitleName.text = "${plant_nicname}와 함께한지 D+"
                            binding.homeTitleDay.text = date.toString()
                        }
                        else {
                            binding.homeTitleName.text = "아직 나의 반려식물을 찾지 못했어요"
                            binding.homeTitleDay.text = ""
                        }
                        if (plant_img != null){
                            Glide.with(requireContext())
                                .load(plant_img)
                                .into(binding.homePlantImg)
                        }
                        if (soulmate_plant != null){
                            binding.homeSearchName.text = "ㄴ ${soulmate_plant}"
                            binding.homeSearchName.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                        }
                        else {
                            binding.homeSearchName.text = "아직 새롭게 찾은 식물이 없어요"
                            binding.homeSearchName.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
                        }
                    } else {
                        Log.d("FragmentHome", "API 호출 성공하지만 응답 에러: ${plantResponse?.message}")
                    }
                } else {
                    Log.d("FragmentHome", "API 호출 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.HomeResponse>, t: Throwable) {
                Log.d("FragmentHome", "API 호출 실패: ${t.message}")
            }
        })
    }
}