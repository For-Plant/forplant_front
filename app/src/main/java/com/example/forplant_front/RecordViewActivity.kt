package com.example.forplant_front

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityRecordViewBinding
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordViewBinding
    private var position: Int = -1  // 아이템의 위치를 저장할 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        //임시로 데이터 넣어주기
//        binding.viewDateTv.text = "2024.08.17"
//        binding.viewPlantnameTv.text = "고기친구"
//        binding.viewWriteEt.text = "오늘은 식목일을 맞이하여 오래 키울 수 있는 식물을 하나 샀다. 선인장을 샀는데, 쪼끄만 게 너무 귀엽다. 이름은 선쪼꼬. 커다랗게 커질 때까지 열심히 키워서 자랑할거다. 꺄르륵 안녕하세요 ㅋ"

        // 전달받은 식물 이름과 날짜, 그리고 포지션을 받아오기
        val plantNickname = intent.getStringExtra("PLANT_NICKNAME") ?: ""
        val date = intent.getStringExtra("DATE") ?: ""
        position = intent.getIntExtra("POSITION", -1)  // 아이템의 위치 받아오기

        // 토큰 가져오기
        val token = MyApplication.getUser().getString("jwt", "") ?: ""

        // API 호출
        fetchRecordContent(token, plantNickname, date)

        goBack()
        //deleteRecord()
    }

    private fun goBack() {
        binding.viewBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun deleteRecord(token: String, plantNickname: String, date: String) {
        binding.viewTrashIv.setOnClickListener {
            //다이얼로그 띄워주기
            showCustomDialog(token, plantNickname, date)
            Toast.makeText(this, "일지가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun showCustomDialog(token: String, plantNickname: String, date: String) {
        // 다이얼로그 레이아웃을 inflate
        val dialogView = layoutInflater.inflate(R.layout.dialog_deleterecord, null)

        // AlertDialog.Builder를 사용하여 다이얼로그 생성
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        // AlertDialog 생성
        val alertDialog: AlertDialog = builder.create()

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // AlertDialog 표시
        alertDialog.show()

        // 다이얼로그 내부의 ImageButton 참조
        val cancelButton = dialogView.findViewById<Button>(R.id.delrecord_cancel_btn)
        val confirmButton = dialogView.findViewById<Button>(R.id.delrecord_delete_btn)

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

//    private fun deleteRecordFromServer(token: String, plantNickname: String, date: String) {
//        // 텍스트 데이터를 RequestBody로 변환
//        val name = RequestBody.create(MultipartBody.FORM, "선인장")
//        val nickname = RequestBody.create(MultipartBody.FORM, plantNickname)
//        val createdAt = RequestBody.create(MultipartBody.FORM, "2024-07-30")
//
//        // 파일을 MultipartBody.Part로 변환 (여기서 "plant_img"는 실제 파일 이름입니다.)
//        val file = /* 여기에 파일 참조를 추가하세요 */
//        val requestFile = RequestBody.create(MultipartBody.FORM, file)
//        val plantImg = MultipartBody.Part.createFormData("plant_img", file.name, requestFile)
//
//        val call = RetrofitObject.getRetrofitService.deletePlantRecord(
//            token = token,
//            name = name,
//            nickname = nickname,
//            createdAt = createdAt,
//            plantImg = plantImg,
//            plantNickname = plantNickname,
//            date = date
//        )
//
//        call.enqueue(object : Callback<RetrofitClient2.ResponseDeleteRecord> {
//            override fun onResponse(call: Call<RetrofitClient2.ResponseDeleteRecord>, response: Response<RetrofitClient2.ResponseDeleteRecord>) {
//                if (response.isSuccessful && response.body()?.isSuccess == true) {
//                    // 삭제 성공시 처리
//                    Toast.makeText(this@RecordViewActivity, "일지가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
//                    finish()  // 현재 액티비티 종료
//                } else {
//                    Log.d("RecordViewActivity", "삭제 실패: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.ResponseDeleteRecord>, t: Throwable) {
//                Log.d("RecordViewActivity", "API 호출 실패: ${t.message}")
//            }
//        })
//    }

    private fun fetchRecordContent(token: String, plantNickname: String, date: String) {
        val call = RetrofitObject.getRetrofitService.getPlantRecordContent(token, plantNickname, date)
        call.enqueue(object : Callback<RetrofitClient2.ResponseCheckRecord> {
            override fun onResponse(call: Call<RetrofitClient2.ResponseCheckRecord>, response: Response<RetrofitClient2.ResponseCheckRecord>) {
                if (response.isSuccessful) {
                    val recordContent = response.body()?.result?.content
                    Log.d("RecordViewActivity", "recordContent: $recordContent")
                    if (recordContent != null) {
                        // 받은 내용을 UI에 반영
                        binding.viewWriteEt.text = recordContent
                        binding.viewDateTv.text = date
                        binding.viewPlantnameTv.text = plantNickname
                    } else {
                        Log.d("RecordViewActivity", "일지 내용이 비어있습니다.")
                    }
                } else {
                    Log.d("RecordViewActivity", "응답이 성공적이지 않습니다: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseCheckRecord>, t: Throwable) {
                Log.d("RecordViewActivity", "API 호출 실패: ${t.message}")
            }
        })
    }
}