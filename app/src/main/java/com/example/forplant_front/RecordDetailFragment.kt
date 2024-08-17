package com.example.forplant_front

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.forplant_front.databinding.FragmentRecordDetailBinding

class RecordDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecordDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordDetailBinding.inflate(inflater, container, false)

        goBack()
        addRecord()

        return binding.root
    }

    private fun goBack() {
        binding.detailBackBtn.setOnClickListener {
            //작성한 내용이 없다면
            parentFragmentManager.popBackStack()
            //작성한 내용이 있다면
            //다이얼로그 띄우기
        }
    }

    private fun addRecord() {
        //등록된 내용이 없을 때
//        binding.detailNoactiveBtn.visibility = View.VISIBLE
//        binding.detailActiveBtn.visibility = View.GONE

        //등록된 내용이 있을 때
        binding.detailNoactiveBtn.visibility = View.GONE
        binding.detailActiveBtn.visibility = View.VISIBLE
        binding.detailActiveBtn.setOnClickListener {
            Toast.makeText(requireContext(), "일지가 등록되었습니다.", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

    }
}