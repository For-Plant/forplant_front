package com.example.forplant_front

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityFindtestChoice2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class findtest_choice2 : AppCompatActivity() {
    private lateinit var binding: ActivityFindtestChoice2Binding
    private lateinit var user: SharedPreferences
    private var question = 1
    private lateinit var answersMap: MutableMap<String, Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFindtestChoice2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // SharedPreferences에서 JWT 토큰 가져오기
        user = getSharedPreferences("user", Context.MODE_PRIVATE)
        val token = user.getString("jwt", "") ?: ""

        // Intent로부터 question 값과 answersMap을 받아옴
        question = intent.getIntExtra("question", 1)
        Log.d("findtest_choice1",question.toString())
        @Suppress("UNCHECKED_CAST")
        answersMap = (intent.getSerializableExtra("answersMap") as? HashMap<String, Int>)?.toMutableMap() ?: mutableMapOf()

        // API 호출
        fetchQuestionData(token, question)

        binding.findtestATop.setOnClickListener {
            saveAnswerAndProceed(0)
        }
        binding.findtestABottom.setOnClickListener {
            saveAnswerAndProceed(1)
        }
        binding.back.setOnClickListener { finish() }
    }
    private fun fetchQuestionData(token: String, questionNum: Int) {
        val call = RetrofitObject.getRetrofitService.getQuestion(token, questionNum)
        call.enqueue(object : Callback<RetrofitClient2.findtestchoiceResponse> {
            override fun onResponse(call: Call<RetrofitClient2.findtestchoiceResponse>, response: Response<RetrofitClient2.findtestchoiceResponse>) {
                if (response.isSuccessful) {
                    val questionResponse = response.body()
                    if (questionResponse != null && questionResponse.isSuccess) {
                        // result가 리스트이므로, 첫 번째 항목을 사용
                        val result = questionResponse.result.firstOrNull()
                        if (result != null) {
                            binding.findtestQ.text = result.question
                            binding.findtestATop.text = result.answer_a
                            binding.findtestABottom.text = result.answer_b
                        }
                    } else {
                        Log.d("findtest_choice1", "API 호출 성공하지만 응답 데이터에 문제가 있습니다.")
                    }
                } else {
                    Log.d("findtest_choice1", "API 호출 실패: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.findtestchoiceResponse>, t: Throwable) {
                Log.d("findtest_choice1", "API 호출 실패: ${t.message}")
            }
        })
    }
    private fun saveAnswerAndProceed(answer: Int) {
        when (question) {
            1 -> answersMap["EI1"] = answer
            2 -> answersMap["EI2"] = answer
            3 -> answersMap["EI3"] = answer
            4 -> answersMap["SN1"] = answer
            5 -> answersMap["SN2"] = answer
            6 -> answersMap["SN3"] = answer
            7 -> answersMap["TF1"] = answer
            8 -> answersMap["TF2"] = answer
            9 -> answersMap["TF3"] = answer
            10 -> answersMap["JP1"] = answer
            11 -> answersMap["JP2"] = answer
            12 -> answersMap["JP3"] = answer
        }

        // 이전 액티비티로 이동하면서 question 값을 1 증가시켜 전달
        if (question == 12){
            val intent = Intent(this, findtest_wait::class.java)
            intent.putExtra("answersMap", HashMap(answersMap))
            startActivity(intent)
        }
        else{
            val intent = Intent(this, findtest_choice1::class.java)
            intent.putExtra("question", question + 1)
            intent.putExtra("answersMap", HashMap(answersMap))  // HashMap으로 변환하여 전달
            startActivity(intent)
        }
    }
}