package com.example.forplant_front

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.chatBackBtn.setOnClickListener {
            finish()
        }

        //messageList = ArrayList()
        val messageAdapter: MessageAdapter = MessageAdapter()
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
            }

            //send(SendChatReq(roomId, message), messageAdapter)
        }

        //이전 대화 목록
        binding.chatChatlistBtn.setOnClickListener {
            val intent = Intent(this, ChatlistActivity::class.java)
            startActivity(intent)
        }
    }


//    private fun send(sendReq: SendChatReq, messageAdapter: MessageAdapter) {
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