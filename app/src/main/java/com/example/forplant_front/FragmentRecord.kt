package com.example.forplant_front

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.forplant_front.databinding.FragmentRecordBinding
import com.example.forplant_front.databinding.ItemPlantBinding
import com.example.forplant_front.databinding.RecordBottomsheetBinding


class FragmentRecord: Fragment() {
    private lateinit var binding: FragmentRecordBinding
    private lateinit var _binding: RecordBottomsheetBinding
    private lateinit var __binding: ItemPlantBinding
    private var modal: RecordBottomSheet? = null
    //BottomSheetDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        _binding = RecordBottomsheetBinding.inflate(inflater, container, false)
        binding.recordPlusBtn.setOnClickListener {
            //바텀시트 클릭 이벤트 처리
            showBottomSheet()
        }

        clickBottomSheet()

        return binding.root
    }

    private fun showBottomSheet() {
        val modal = RecordBottomSheet()
        modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal.show(parentFragmentManager, RecordBottomSheet.TAG)
    }

    private fun clickBottomSheet() {
        _binding.btmstAddplantBtn.setOnClickListener {
            // 바텀시트를 닫고 FragmentAddPlant로 전환
            modal?.dismiss()  // 바텀시트 닫기
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.record_record_cl, RecordAddPlantFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        _binding.btmstSetrepBtn.setOnClickListener {
            modal?.dismiss()  // 바텀시트 닫기

        }

        _binding.btmstDeathplantBtn.setOnClickListener {
            modal?.dismiss()  // 바텀시트 닫기

        }

        _binding.btmstDeleteplantBtn.setOnClickListener {
            modal?.dismiss()  // 바텀시트 닫기

        }
    }
}