package com.example.forplant_front

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityRecordDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        addRecord()
    }

    private fun goBack() {
        binding.detailBackBtn.setOnClickListener {
            if (binding.detailWriteEt.toString().isNotEmpty()) {  // 작성한 내용이 있다면
                //다이얼로그 띄우기
                showCustomDialog()
            } else {  // 작성한 내용이 없다면
                finish()
            }
        }
    }

    private fun showCustomDialog() {
        // 다이얼로그 레이아웃을 inflate
        val dialogView = layoutInflater.inflate(R.layout.gettingout_dialog, null)

        // AlertDialog.Builder를 사용하여 다이얼로그 생성
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        // AlertDialog 생성
        val alertDialog: AlertDialog = builder.create()

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // AlertDialog 표시
        alertDialog.show()

        // 다이얼로그의 Window 속성 조정
        val window = alertDialog.window
        window?.setLayout(296.dpToPx(this), 149.dpToPx(this))

        // 다이얼로그 내부의 ImageButton 참조
        val cancelButton = dialogView.findViewById<Button>(R.id.cancel_btn)
        val confirmButton = dialogView.findViewById<Button>(R.id.delete_btn)

        // 취소 버튼 클릭 리스너 설정
        cancelButton.setOnClickListener {
            // 취소 버튼을 눌렀을 때 수행할 동작
            alertDialog.dismiss() // 다이얼로그 닫기
            // 추가적인 작업 수행 가능
        }

        // 확인 버튼 클릭 리스너 설정
        confirmButton.setOnClickListener {
            finish()
            alertDialog.dismiss() // 다이얼로그 닫기
            // 추가적인 작업 수행 가능
        }

        // AlertDialog 표시
        alertDialog.show()
    }

    // dp를 px로 변환하는 확장 함수
    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

    private fun addRecord() {
        val content = binding.detailWriteEt.text.toString()

        if (content.isNotEmpty()) {  // 등록된 내용이 있을 때
            binding.detailNoactiveBtn.visibility = View.GONE
            binding.detailActiveBtn.visibility = View.VISIBLE

            val token = MyApplication.getUser().getString("jwt", "") ?: ""
            val plantNickname = intent.getStringExtra("PLANT_NICKNAME") ?: ""

            val request = RetrofitClient2.RequestWriteRecord(content)
            val call = RetrofitObject.getRetrofitService.writePlantRecord(token, plantNickname, request)

            call.enqueue(object : Callback<RetrofitClient2.ResponseWriteRecord> {
                override fun onResponse(call: Call<RetrofitClient2.ResponseWriteRecord>, response: Response<RetrofitClient2.ResponseWriteRecord>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d("RecordDetailActivity", "responseBody: $responseBody")
                        if (responseBody != null && responseBody.isSuccess) {
                            Log.d("RecordDetailActivity", "일지가 등록되었습니다. Record ID: ${responseBody.result.record_id}")
                            finish()
                        } else {
                            Log.d("RecordDetailActivity", "등록에 실패했습니다: ${responseBody?.message}")
                        }
                    } else {
                        Log.d("RecordDetailActivity", "Response not successful: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.ResponseWriteRecord>, t: Throwable) {
                    Log.d("RecordDetailActivity", "API Call failed: ${t.message}")
                }
            })

            binding.detailActiveBtn.setOnClickListener {
                Toast.makeText(this, "일지가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {  // 등록된 내용이 없을 때
            binding.detailNoactiveBtn.visibility = View.VISIBLE
            binding.detailActiveBtn.visibility = View.GONE
            Log.d("RecordDetailActivity", "Content is empty")
        }
    }
}