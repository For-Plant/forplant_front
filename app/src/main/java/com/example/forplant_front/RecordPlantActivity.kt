package com.example.forplant_front

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.ActivityRecordPlantBinding
import com.example.forplant_front.databinding.ActivityRipListOnedayBinding

class RecordPlantActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecordPlantBinding
    private lateinit var adapter: RecordPlantRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        binding.recordPlusBtn.setOnClickListener {
            showBottomSheet()
        }
        showPlantImage()

        //아이템이 하나라도 있을 경우
        binding.recordNolistTv.visibility = View.GONE
        binding.recordRecordRv.visibility = View.VISIBLE
        //아이템 추가
        val itemList = listOf("2024.08.17", "2024.08.07")
        setupRecyclerView(itemList)
    }

    private fun goBack() {
        binding.recordBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun showBottomSheet() {
        val modal = RecordPlantBottomSheet()
        modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal.show(supportFragmentManager, RecordPlantBottomSheet.TAG)
    }

    private fun showPlantImage() {
        //등록한 사진이 있다면 등록한 사진 보여주기
        //없다면 기본 이미지 사용
    }

    private fun setupRecyclerView(recordlist: List<String>) {
        // RecyclerView의 레이아웃 매니저 설정
        binding.recordRecordRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // RecyclerView의 어댑터 설정
        adapter = RecordPlantRVAdapter(recordlist)
        binding.recordRecordRv.adapter = adapter
    }
}