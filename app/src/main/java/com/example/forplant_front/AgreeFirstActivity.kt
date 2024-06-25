package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.forplant_front.databinding.ActivityAgreefirstBinding

class AgreeFirstActivity: AppCompatActivity() {
    lateinit var binding: ActivityAgreefirstBinding
    private var isCheckedImageView3 = false
    private var isCheckedImageView4 = false
    private var isCheckedImageView5 = false
    private var isCheckedImageView6 = false
    private var isCheckedImageView7 = false
    private var finalcheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreefirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView3.setOnClickListener {
            isCheckedImageView3 = !isCheckedImageView3
            setCheckState(isCheckedImageView3, binding.imageView3)
            if (isCheckedImageView3) {
                // All other checkboxes should be checked when imageView3 is checked
                isCheckedImageView4 = true
                isCheckedImageView5 = true
                isCheckedImageView6 = true
                isCheckedImageView7 = true
                setCheckState(isCheckedImageView4, binding.imageView4)
                setCheckState(isCheckedImageView5, binding.imageView5)
                setCheckState(isCheckedImageView6, binding.imageView6)
                setCheckState(isCheckedImageView7, binding.imageView7)
            }
            else
            {
                isCheckedImageView4 = false
                isCheckedImageView5 = false
                isCheckedImageView6 = false
                isCheckedImageView7 = false
                setCheckState(isCheckedImageView4, binding.imageView4)
                setCheckState(isCheckedImageView5, binding.imageView5)
                setCheckState(isCheckedImageView6, binding.imageView6)
                setCheckState(isCheckedImageView7, binding.imageView7)
            }
            updateAgreeButton()
        }

        binding.imageView4.setOnClickListener {
            isCheckedImageView4 = !isCheckedImageView4
            setCheckState(isCheckedImageView4, binding.imageView4)
            updateAgreeButton()
        }

        binding.imageView5.setOnClickListener {
            isCheckedImageView5 = !isCheckedImageView5
            setCheckState(isCheckedImageView5, binding.imageView5)
            updateAgreeButton()
        }

        binding.imageView6.setOnClickListener {
            isCheckedImageView6 = !isCheckedImageView6
            setCheckState(isCheckedImageView6, binding.imageView6)
            updateAgreeButton()
        }

        binding.imageView7.setOnClickListener {
            isCheckedImageView7 = !isCheckedImageView7
            setCheckState(isCheckedImageView7, binding.imageView7)
            updateAgreeButton()
        }

        binding.imageView10.setOnClickListener {
            if (finalcheck) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.btnRc2.setOnClickListener {
            val intent = Intent(this, Agree2Activity::class.java)
            startActivity(intent)
        }
        binding.btnRc3.setOnClickListener {
            val intent = Intent(this, Agree3Activity::class.java)
            startActivity(intent)
        }
    }

    private fun setCheckState(isChecked: Boolean, imageView: ImageView) {
        if (isChecked) {
            imageView.setImageResource(R.drawable.btn_checkbox_checked)
            binding.imageView10.setImageResource(R.drawable.btn_agree_green)
        } else {
            imageView.setImageResource(R.drawable.btn_checkbox)
            binding.imageView10.setImageResource(R.drawable.btn_agree_gray)
        }
    }
    private fun updateAgreeButton() {
        if (isCheckedImageView4 && isCheckedImageView5 && isCheckedImageView6 && isCheckedImageView7) {
            binding.imageView3.setImageResource(R.drawable.btn_checkbox_checked)
            binding.imageView10.setImageResource(R.drawable.btn_agree_green)
            finalcheck=true
        } else {
            binding.imageView3.setImageResource(R.drawable.btn_checkbox)
            binding.imageView10.setImageResource(R.drawable.btn_agree_gray)
            finalcheck=false
        }
    }
}