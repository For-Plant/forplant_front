package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forplant_front.databinding.RecordPlantBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecordPlantBottomSheet : BottomSheetDialogFragment()  {
    private var _binding: RecordPlantBottomsheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = RecordPlantBottomsheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btmstEditplantBtn.setOnClickListener {
            dismiss()  // 바텀시트 닫기
            // RecordAddPlantActivity로 전환
            val intent = Intent(requireContext(), RecordEditplantActivity::class.java)
            startActivity(intent)
        }

        binding.btmstAddrecordBtn.setOnClickListener {
            dismiss()  // 바텀시트 닫기

            // RecordDetailActivity로 전환
            val intent = Intent(requireContext(), RecordDetailActivity::class.java)
            startActivity(intent)
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