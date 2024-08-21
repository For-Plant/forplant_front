package com.example.forplant_front

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityChatlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatlistActivity: AppCompatActivity() {

    private lateinit var binding: ActivityChatlistBinding
    private lateinit var chatListAdapter: ChatlistRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listBackBtn.setOnClickListener {
            finish()
        }

        //리사이클러뷰 어댑터
        // Initialize RecyclerView and adapter
        chatListAdapter = ChatlistRVAdapter()
        binding.listListRv.layoutManager = LinearLayoutManager(this)
        binding.listListRv.adapter = chatListAdapter

        // Load chat list from the server
        loadChatList()
    }

    private fun loadChatList() {
        val token = MyApplication.getUser().getString("jwt", null)

        if (token != null) {
            RetrofitObject.getRetrofitService.getChatList(token)
                .enqueue(object : Callback<RetrofitClient2.ResponseChatList> {
                    override fun onResponse(call: Call<RetrofitClient2.ResponseChatList>, response: Response<RetrofitClient2.ResponseChatList>) {
                        if (response.isSuccessful) {
                            val chatListResponse = response.body()
                            if (chatListResponse != null && chatListResponse.isSuccess) {
                                chatListAdapter.updateChatList(chatListResponse.result)
                            } else {
                                Log.e("ChatlistActivity", "Failed to load chat list: ${chatListResponse?.message}")
                            }
                        } else {
                            Log.e("ChatlistActivity", "Failed to get a valid response")
                        }
                    }

                    override fun onFailure(call: Call<RetrofitClient2.ResponseChatList>, t: Throwable) {
                        Log.e("ChatlistActivity", "API call failed: ${t.message}")
                    }
                })
        } else {
            Log.e("ChatlistActivity", "JWT token is missing")
        }
    }
}