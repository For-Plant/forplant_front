package com.example.forplant_front

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivitySignBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding
    private var isChecked: Boolean = false

    private lateinit var user: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApplication.initializeUser(this)
        user = MyApplication.getUser()
        editor = user.edit()

        binding.textView16.setOnClickListener {
            val intent = Intent(this, AgreeFirstActivity::class.java)
            startActivity(intent)
        }

//        binding.button.setOnClickListener {
//            val id = binding.IDtext.text.toString()
//            val password = binding.Passwordtext.text.toString()
//
//            val call = RetrofitObject.getRetrofitService.login(RetrofitClient2.Requestlogin(id, password))
//            call.enqueue(object : Callback<RetrofitClient2.Responselogin> {
//                override fun onResponse(call: Call<RetrofitClient2.Responselogin>, response: Response<RetrofitClient2.Responselogin>) {
//                    Log.d("Retrofit21", response.toString())
//                    if (response.isSuccessful) {
//                        val response = response.body()
//                        Log.d("Retrofit2", response.toString())
//                        if(response != null){
//                            if(response.isSuccess) {
//                                val token = response.result
//                                editor.putString("member_id", id)
//                                editor.putString("password", password)
//                                editor.putString("jwt", token)
//                                editor.apply()
////                                Log.d("logintoken", token) #이거 지금 null값이라 주석 풀면 안됨
//                                val intent =
//                                    Intent(this@LoginActivity, MainActivity::class.java)
//                                startActivity(intent)
//                            }else{
//                                Toast.makeText(this@LoginActivity, response.message, Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<RetrofitClient2.Responselogin>, t: Throwable) {
//                    val errorMessage = "Call Failed: ${t.message}"
//                    Log.d("Retrofit", errorMessage)
//                }
//            })
//        }
        binding.button.setOnClickListener {
            val intent =
                Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.textView19.setOnClickListener {
            val intent = Intent(this, IdfoundActivity::class.java)
            startActivity(intent)
        }
        binding.textView20.setOnClickListener {
            val intent = Intent(this, PasswordfoundActivity::class.java)
            startActivity(intent)
        }
        // 이미지 뷰 클릭 이벤트 리스너 설정
        binding.imageView12.setOnClickListener {
            // 토글 상태 변경
            isChecked = !isChecked
            // 이미지 변경
            if (isChecked) {
                binding.imageView12.setImageResource(R.drawable.btn_checkbox_checked)
            } else {
                binding.imageView12.setImageResource(R.drawable.btn_checkbox)
            }
        }
    }
}
