package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.FragmentRecordBinding
import com.example.forplant_front.databinding.ItemPlantBinding
import com.example.forplant_front.databinding.RecordBottomsheetBinding

class FragmentRecord: Fragment() {
    private lateinit var binding: FragmentRecordBinding
    private var modal: RecordBottomSheet? = null
    private lateinit var adapter: RecordRVAdapter
    //BottomSheetDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        binding.recordPlusBtn.setOnClickListener {
            //바텀시트 클릭 이벤트 처리
            showBottomSheet()
        }

        //아이템이 하나라도 있을 경우
        binding.recordNoplantTv.visibility = View.GONE
        binding.recordPlantlistRv.visibility = View.VISIBLE
        //아이템 추가
        val itemList = listOf("아이템 1", "아이템 2")
        setupRecyclerView(itemList)

        return binding.root
    }

    private fun showBottomSheet() {
        modal = RecordBottomSheet()
        modal?.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal?.show(parentFragmentManager, RecordBottomSheet.TAG)
    }

    private fun setupRecyclerView(itemList: List<String>) {
        // RecyclerView의 레이아웃 매니저 설정
        binding.recordPlantlistRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // RecyclerView의 어댑터 설정
        adapter = RecordRVAdapter(itemList)
        binding.recordPlantlistRv.adapter = adapter
    }
}