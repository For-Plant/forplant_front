package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forplant_front.databinding.FragmentHomeBinding


class FragmentHome: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeTestBtn.setOnClickListener {
            val intent = Intent(activity,findtest_home::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}