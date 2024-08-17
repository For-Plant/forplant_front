package com.example.forplant_front

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivityRecordViewBinding

class RecordViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //임시로 데이터 넣어주기
        binding.viewDateTv.text = "2024.08.17"
        binding.viewPlantnameTv.text = "고기친구"
        binding.viewWriteEt.text = "오늘은 식목일을 맞이하여 오래 키울 수 있는 식물을 하나 샀다. 선인장을 샀는데, 쪼끄만 게 너무 귀엽다. 이름은 선쪼꼬. 커다랗게 커질 때까지 열심히 키워서 자랑할거다. 꺄르륵 안녕하세요 ㅋ"

        goBack()
        deleteRecord()
    }

    private fun goBack() {
        binding.viewBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun deleteRecord() {
        binding.viewTrashIv.setOnClickListener {
            //다이얼로그 띄워주기
            Toast.makeText(this, "일지가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}