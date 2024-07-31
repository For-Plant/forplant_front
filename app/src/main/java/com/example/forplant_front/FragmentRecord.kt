package com.example.forplant_front

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.forplant_front.databinding.FragmentRecordBinding


class FragmentRecord: Fragment() {
    private lateinit var binding: FragmentRecordBinding
    //BottomSheetDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        binding.recordPlusBtn.setOnClickListener {
            //바텀시트 클릭 이벤트 처리
            modalWithRoundCorner()
        }

        return binding.root
    }

    private fun modalWithRoundCorner() {
        val modal = ModalBottomSheet()
        modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
        modal.show(parentFragmentManager, ModalBottomSheet.TAG)
    }
}