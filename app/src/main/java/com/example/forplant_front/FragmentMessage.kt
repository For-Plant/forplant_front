package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.FragmentMessageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentMessage: Fragment() {
    private lateinit var binding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)

        binding.messageGochatBtn.setOnClickListener {
            startChatRoom()
        }

        return binding.root
    }

    private fun startChatRoom() {
        // Get the stored JWT token
        val token = MyApplication.getUser().getString("jwt", null)

        if (token != null) {
            val call = RetrofitObject.getRetrofitService.startChat(token)
            call.enqueue(object : Callback<RetrofitClient2.ResponseStartChat> {
                override fun onResponse(call: Call<RetrofitClient2.ResponseStartChat>, response: Response<RetrofitClient2.ResponseStartChat>) {
                    if (response.isSuccessful) {
                        val startChatResponse = response.body()
                        Log.d("StartChat", "chatresponse: $startChatResponse")
                        if (startChatResponse != null && startChatResponse.isSuccess) {
                            val roomId = startChatResponse.result.roomId
                            Log.d("StartChat", "Chat room created with ID: $roomId")

                            // Proceed to the ChatActivity if needed
                            val intent = Intent(activity, ChatActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.e("StartChat", "Failed to create chat room")
                        }
                    } else {
                        Log.e("StartChat", "Response error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.ResponseStartChat>, t: Throwable) {
                    Log.e("StartChat", "API call failed: ${t.message}")
                }
            })
        } else {
            Log.e("StartChat", "Token is missing")
            Toast.makeText(activity, "Token is missing", Toast.LENGTH_SHORT).show()
        }
    }
}