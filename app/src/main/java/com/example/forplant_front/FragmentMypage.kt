package com.example.forplant_front

import android.app.TaskStackBuilder
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.FragmentMypageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentMypage: Fragment() {
    private lateinit var binding: FragmentMypageBinding
    private lateinit var user: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var token: String
    private lateinit var memberId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        // 사용자 SharedPreferences 초기화
        user = MyApplication.getUser()
        token = user.getString("jwt", "").toString()
        memberId = user.getString("member_id", "").toString()

        binding.textView52.text = memberId

        binding.imageView14.setOnClickListener {
            val intent = Intent(requireContext(), ProfilemodifyActivity::class.java)
            startActivity(intent)
        }

        binding.textView54.setOnClickListener {
            val intent = Intent(requireContext(), MypagePlantActivity::class.java)
            startActivity(intent)
        }

        binding.texttitle2.setOnClickListener {
            val intent = Intent(requireContext(), MypageRipActivity::class.java)
            startActivity(intent)
        }

        binding.texttitle4.setOnClickListener {
            val intent = Intent(requireContext(), MypageSeccsionActivity::class.java)
            startActivity(intent)
        }
        binding.imageView32.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }

        loadProfileImage()
        return binding.root
    }
    private fun loadProfileImage() {
        val call = RetrofitObject.getRetrofitService.getmypage("$token")
        call.enqueue(object : Callback<RetrofitClient2.Responsegetmypage> {
            override fun onResponse(call: Call<RetrofitClient2.Responsegetmypage>, response: Response<RetrofitClient2.Responsegetmypage>) {
                if (response.isSuccessful) {
                    Log.d("Retrofit4", response.toString())
                    // response.body()를 통해 Responsegetmypage 객체에 접근
                    val profileData = response.body()?.result

                    profileData?.let {
                        // 유저의 memberId를 설정
                        binding.textView52.text = it.user.memberId

                        // 이미지를 가져와서 이미지뷰에 설정
                        val imageUrl = it.user.profileImg
                        if (imageUrl != null) {
                            // 이미지 URL이 null이 아닌 경우 Glide를 사용하여 이미지 로드
                            Glide.with(requireContext())
                                .load(imageUrl)
                                .into(binding.imageView18)

                            binding.soldoutlayout.visibility= View.GONE
                        } else {
                            binding.soldoutlayout.visibility= View.VISIBLE
                        }

                        // 닉네임을 가져와서 SharedPreferences에 저장
                        val nickName = it.user.nickname
                        if (nickName != null) {
                            binding.textView51.text = nickName
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        response.body()?.message ?: "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<RetrofitClient2.Responsegetmypage>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("Retrofit", errorMessage)
            }
        })
    }
    override fun onResume() {
        super.onResume()
        // 프로필 이미지 다시 불러오기
        loadProfileImage()
    }
}