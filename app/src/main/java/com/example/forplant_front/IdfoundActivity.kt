package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityIdfoundBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IdfoundActivity : AppCompatActivity() {
    lateinit var binding: ActivityIdfoundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.notshowinglay.visibility= View.GONE
        binding.textView72.visibility= View.GONE
        binding.imageView2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val phonenum = binding.Idedittext.text.toString()
            val username = binding.Idedittext2.text.toString()

            val call = RetrofitObject.getRetrofitService.findid(RetrofitClient2.Requestfindid(phonenum, username))
            call.enqueue(object : Callback<RetrofitClient2.Responsefindid> {
                override fun onResponse(call: Call<RetrofitClient2.Responsefindid>, response: Response<RetrofitClient2.Responsefindid>) {
                    Log.d("Retrofit21", response.toString())
                    if (response.isSuccessful) {
                        val response = response.body()
                        Log.d("Retrofit2", response.toString())
                        if(response != null){
                            if(response.isSuccess) {
                                binding.textView72.visibility= View.GONE
                                val member_id = response.result.member_id
                                binding.textView49.text = member_id
                                binding.notshowinglay.visibility= View.VISIBLE
                            }else{
                                binding.notshowinglay.visibility= View.GONE
                                binding.textView72.visibility= View.VISIBLE
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.Responsefindid>, t: Throwable) {
                    val errorMessage = "Call Failed: ${t.message}"
                    Log.d("Retrofit", errorMessage)
                }
            })
        }

        binding.textView44.setOnClickListener {
            val intent = Intent(this, PasswordfoundActivity::class.java)
            startActivity(intent)
        }
    }
}
