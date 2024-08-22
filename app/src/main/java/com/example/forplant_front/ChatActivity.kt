package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityChatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomId = intent.getIntExtra("roomId", 1)
        val roomTitle = intent.getStringExtra("roomTitle") ?: "대화"

        // 채팅방 제목 설정
        binding.chatTitleTv.text = roomTitle

        // 채팅 메시지 불러오기
        loadChatMessages(roomTitle)

        binding.chatBackBtn.setOnClickListener {
            finish()
        }

        //messageList = ArrayList()
        val messageAdapter = MessageAdapter()
        binding.chatRv.layoutManager = LinearLayoutManager(this)
        binding.chatRv.adapter = messageAdapter

        val firstm = intent.getStringExtra("firstm") ?: "안녕하세요, 무엇을 도와드릴까요?"
        messageAdapter.apply {
            addItem(MessageModel.ReceiverMessage(firstm))
        }

        //전송
        binding.btnChatSend.setOnClickListener {
            val message = binding.chatInputEt.text.toString()
            val roomId = intent.getIntExtra("roomId", 1)

            if (message.isNotBlank()) {
                messageAdapter.apply {
                    addItem(MessageModel.SenderMessage(message))
                }

                // 메시지 전송 후 입력 필드 초기화
                binding.chatInputEt.text.clear()

                val roomId = intent.getIntExtra("roomId", 1)

                val token = MyApplication.getUser().getString("jwt", null)
                if (token != null) {
                    sendMessageToAPI(token, message, messageAdapter)
                } else {
                    Log.e("ChatActivity", "JWT token is missing")
                }
            }
        }

        //이전 대화 목록
        binding.chatChatlistBtn.setOnClickListener {
            val intent = Intent(this, ChatlistActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadChatMessages(roomTitle: String) {
        val token = MyApplication.getUser().getString("jwt", null)
        if (token != null) {
            RetrofitObject.getRetrofitService.getChatMessages(token, roomTitle)
                .enqueue(object : Callback<RetrofitClient2.ResponseChatMessages> {
                    override fun onResponse(call: Call<RetrofitClient2.ResponseChatMessages>, response: Response<RetrofitClient2.ResponseChatMessages>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            Log.d("ChatActivity", "responseBody: $responseBody")
                            if (responseBody != null && responseBody.isSuccess) {
                                val messageAdapter = binding.chatRv.adapter as MessageAdapter
                                responseBody.result.forEach { message ->
                                    if (message.role == "user") {
                                        messageAdapter.addItem(MessageModel.SenderMessage(message.content))
                                    } else {
                                        messageAdapter.addItem(MessageModel.ReceiverMessage(message.content))
                                    }
                                }
                            } else {
                                Log.e("ChatActivity", "메시지 불러오기 실패: ${responseBody?.message}")
                            }
                        } else {
                            Log.e("ChatActivity", "유효한 응답을 받지 못했습니다.")
                        }
                    }

                    override fun onFailure(call: Call<RetrofitClient2.ResponseChatMessages>, t: Throwable) {
                        Log.e("ChatActivity", "API 호출 실패: ${t.message}")
                    }
                })
        } else {
            Log.e("ChatActivity", "JWT 토큰이 없습니다.")
        }
    }

    private fun sendMessageToAPI(token: String, message: String, messageAdapter: MessageAdapter) {
        val Requestmessage = RetrofitClient2.RequestMessage(message)

        RetrofitObject.getRetrofitService.sendMessage(token, Requestmessage)
            .enqueue(object : Callback<RetrofitClient2.ResponseMessage> {
                override fun onResponse(call: Call<RetrofitClient2.ResponseMessage>, response: Response<RetrofitClient2.ResponseMessage>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.isSuccess) {
                            messageAdapter.addItem(MessageModel.ReceiverMessage(responseBody.result.message))
                        } else {
                            Log.e("ChatActivity", "API Response failed: ${responseBody?.message}")
                        }
                    } else {
                        Log.e("ChatActivity", "Failed to get a valid response from API")
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.ResponseMessage>, t: Throwable) {
                    Log.e("ChatActivity", "API call failed: ${t.message}")
                }
            })
    }

//    private fun send(sendReq: RequestMessage, messageAdapter: MessageAdapter) {
//        messageAdapter.apply {
//            addItem(MessageModel.SenderMessage(binding.etChatInput.text.toString()))
//        }
//
//        RetrofitClient.chatApi.sendChat(GlobalApplication.spf.Jwt, sendReq).enqueue(object : Callback<SendChatRes> {
//            override fun onResponse(call: Call<SendChatRes>, response: Response<SendChatRes>) {
//                if(response.isSuccessful){
//                    val sendChatResult: SendChatRes? = response.body()
//                    Log.d("SENDCHAT", "onResponse 성공: " + sendChatResult?.toString())
//
//
//
//                }else{
//                    Log.d("SENDCHAT", "onResponse 실패")
//                }
//            }
//            override fun onFailure(call: Call<SendChatRes>, t: Throwable) {
//                Log.d("SENDCHAT/ERROR", t.message.toString())
//            }
//
//
//        })
//    }
}