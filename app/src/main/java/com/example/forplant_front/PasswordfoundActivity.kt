package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityIdfoundBinding
import com.example.forplant_front.databinding.ActivityPasswordfoundBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class PasswordfoundActivity : AppCompatActivity() {
    lateinit var binding: ActivityPasswordfoundBinding
    private var isPasswordValid = false
    private var isPasswordMatching = false
    var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.notshowing.visibility= View.GONE
        binding.textView62.visibility= View.GONE
        binding.textViewcheck.visibility = View.GONE
        binding.textView62.visibility = View.GONE

        val eyeImageView = binding.imageView11
        val eyeCheckImageView = binding.imageView12

        val textPassword = binding.Idedittext4
        val textPasswordCheck = binding.Idedittext5

        binding.imageView2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }

        eyeImageView.setOnClickListener {
            togglePasswordVisibility(textPassword, eyeImageView)
        }
        eyeCheckImageView.setOnClickListener {
            togglePasswordVisibility(textPasswordCheck, eyeCheckImageView)
        }

        textPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 입력된 비밀번호가 조건을 만족하는지 확인
                isPasswordValid = isPasswordValid(textPassword.text.toString())

                // 비밀번호가 조건을 만족하지 않으면 textchecking의 visibility를 View.VISIBLE로 설정
                if (!isPasswordValid) {
                    binding.textView60.visibility = View.GONE
                    binding.textViewcheck.visibility = View.VISIBLE
                } else {
                    // 비밀번호가 조건을 만족하면 textchecking의 visibility를 View.GONE으로 설정
                    binding.textView60.visibility = View.GONE
                    binding.textViewcheck.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        // textPasswordCheck의 텍스트 변경 감지
        textPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // textPassword와 textPasswordCheck의 값이 같을 때 배경을 업데이트
                isPasswordMatching = textPassword.text.toString() == textPasswordCheck.text.toString()

                if (isPasswordMatching) {
                    binding.textView62.visibility = View.GONE
                } else {
                    binding.textView62.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.button.setOnClickListener {
            val phonenum = binding.Idedittext2.text.toString()
            val username = binding.Idedittext3.text.toString()
            val member_id = binding.Idedittext.text.toString()
            val password = binding.Idedittext4.text.toString()

            val call = RetrofitObject.getRetrofitService.findid(RetrofitClient2.Requestfindid(phonenum, username))
            call.enqueue(object : Callback<RetrofitClient2.Responsefindid> {
                override fun onResponse(call: Call<RetrofitClient2.Responsefindid>, response: Response<RetrofitClient2.Responsefindid>) {
                    Log.d("Retrofit21", response.toString())
                    if (response.isSuccessful) {
                        val response = response.body()
                        Log.d("Retrofit2", response.toString())
                        if(response != null){
                            if(response.isSuccess) {
                                binding.notshowing.visibility = View.VISIBLE
                                binding.button.setBackgroundResource(R.drawable.green_rectangle56)
                                // 비밀번호 유효성 검사 및 일치 여부 확인
                                if (isPasswordValid && isPasswordMatching) {
                                    val changePasswordCall = RetrofitObject.getRetrofitService.changepw(RetrofitClient2.Requestchangepw(member_id, phonenum, username, password))
                                    changePasswordCall.enqueue(object : Callback<RetrofitClient2.Responsechangepw> {
                                        override fun onResponse(call: Call<RetrofitClient2.Responsechangepw>, response: Response<RetrofitClient2.Responsechangepw>) {
                                            if (response.isSuccessful) {
                                                val changePasswordResponse = response.body()
                                                if (changePasswordResponse != null) {
                                                    if (changePasswordResponse.isSuccess) {
                                                        // 비밀번호 변경 성공, 로그인 화면으로 이동
                                                        val intent = Intent(this@PasswordfoundActivity, LoginActivity::class.java).apply {
                                                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                        }
                                                        startActivity(intent)
                                                    } else {
                                                        Toast.makeText(this@PasswordfoundActivity, changePasswordResponse.message, Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                            }
                                        }

                                        override fun onFailure(call: Call<RetrofitClient2.Responsechangepw>, t: Throwable) {
                                            val errorMessage = "Call Failed: ${t.message}"
                                            Log.d("Retrofit", errorMessage)
                                        }
                                    })
                                } else {
                                }
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

        binding.textView41.setOnClickListener {
            val intent = Intent(this, IdfoundActivity::class.java)
            startActivity(intent)
        }

    }
    private fun isPasswordValid(password: String): Boolean {
        // 비밀번호가 8자리 이상이며 영문, 숫자, 특수문자를 포함하는지 확인하는 로직
        val pattern = Pattern.compile("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}")
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
    private fun togglePasswordVisibility(editText: EditText, imageView: ImageView) {
        // 비밀번호 보이기/감추기 기능 구현
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // 비밀번호를 보이도록 변경
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_on)
        } else {
            // 비밀번호를 감추도록 변경
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_off)
        }

        // 커서를 마지막으로 이동하여 비밀번호 글자가 보이도록 함
        editText.setSelection(editText.text.length)
    }
}
