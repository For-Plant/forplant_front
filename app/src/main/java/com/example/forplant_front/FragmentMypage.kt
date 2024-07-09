package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forplant_front.databinding.FragmentMypageBinding


class FragmentMypage: Fragment() {
    private lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        binding.imageView14.setOnClickListener {
            val intent = Intent(requireContext(), ProfilemodifyActivity::class.java)
            startActivity(intent)
        }

        binding.textView54.setOnClickListener {
            val intent = Intent(requireContext(), MypagePlantActivity::class.java)
            startActivity(intent)
        }

        binding.texttitle2.setOnClickListener {
            val intent = Intent(requireContext(), MypageRipActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}