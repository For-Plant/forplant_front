package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.RecordBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecordBottomSheet : BottomSheetDialogFragment()  {
    private var _binding: RecordBottomsheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = RecordBottomsheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btmstAddplantBtn.setOnClickListener {
            dismiss()  // 바텀시트 닫기
            val intent = Intent(requireContext(), RecordAddPlantActivity::class.java)
            startActivity(intent)
        }

        binding.btmstSetrepBtn.setOnClickListener {
            dismiss()  // 바텀시트 닫기

            // 대표 선택 창

        }

        binding.btmstDeleteplantBtn.setOnClickListener {
            dismiss()  // 바텀시트 닫기

            // 삭제 선택 창

        }

        binding.btmstDeathplantBtn.setOnClickListener {
            dismiss()  // 바텀시트 닫기

            // 부고처리 할 식물 선택 창
            // 부고처리 할 식물 정보 전달


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // 메모리 누수 방지
    }

    companion object {
        const val TAG = "BasicBottomModalSheet"
    }
}