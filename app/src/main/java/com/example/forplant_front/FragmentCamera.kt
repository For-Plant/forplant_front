package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.forplant_front.databinding.FragmentCameraBinding


class FragmentCamera: Fragment() {
    private lateinit var binding: FragmentCameraBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)

        binding.cameraNextBtn.setOnClickListener{
            val intent = Intent(activity,camera_page::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}