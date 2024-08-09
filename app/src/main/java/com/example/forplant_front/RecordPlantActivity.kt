package com.example.forplant_front

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.forplant_front.databinding.ActivityRecordPlantBinding
import com.example.forplant_front.databinding.ActivityRipListOnedayBinding

class RecordPlantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordPlantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        showBottomSheet()
        showPlantImage()
    }

    private fun goBack() {
        binding.recordBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun showBottomSheet() {
        val modal = RecordPlantBottomSheet()
        modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal.show(supportFragmentManager, RecordBottomSheet.TAG)
    }

    private fun showPlantImage() {
        //등록한 사진이 있다면 등록한 사진 보여주기
        //없다면 기본 이미지 사용
    }

    //어댑터 사용해서 다음 페이지 넘어가는거 구현해야 함
}