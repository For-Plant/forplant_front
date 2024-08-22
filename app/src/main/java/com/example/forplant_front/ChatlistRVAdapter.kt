package com.example.forplant_front

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ItemChatlistBinding

class ChatlistRVAdapter : RecyclerView.Adapter<ChatlistRVAdapter.ChatViewHolder>() {

    private val chatList = mutableListOf<RetrofitClient2.ChatRoom>()

    fun updateChatList(newChatList: List<RetrofitClient2.ChatRoom>) {
        chatList.clear()
        chatList.addAll(newChatList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ChatViewHolder(private val binding: ItemChatlistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatRoom: RetrofitClient2.ChatRoom) {
            binding.itemChattitleTv.text = chatRoom.roomTitle
            binding.itemDateTv.text = chatRoom.lastMessageTime

            // Set up click listener to open chat activity for the selected chat room
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ChatActivity::class.java).apply {
                    putExtra("roomId", chatRoom.roomId)
                    putExtra("roomTitle", chatRoom.roomTitle)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }
}