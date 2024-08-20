package com.example.forplant_front

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.ItemAimessageBinding
import com.example.forplant_front.databinding.ItemUsermessageBinding

interface MessageModel {
    data class SenderMessage(
        val message: String
    ): MessageModel

    data class ReceiverMessage(
        val message: String
    ): MessageModel
}

class MessageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList = arrayListOf<MessageModel>()

    fun addItem(item: MessageModel){
        itemList.add(item)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_SENDER -> SenderViewHolder.create(parent)
            else -> ReceiverViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SenderViewHolder -> holder.bindItem(itemList[position] as MessageModel.SenderMessage)
            is ReceiverViewHolder -> holder.bindItem(itemList[position] as MessageModel.ReceiverMessage)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]){
            is MessageModel.SenderMessage -> TYPE_SENDER
            is MessageModel.SenderMessage -> TYPE_RECEIVER
            else -> -1
        }

    }
    companion object{
        const val TYPE_SENDER = 0
        const val TYPE_RECEIVER = 1
    }

    class ReceiverViewHolder(private val binding: ItemAimessageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(receiverMessage: MessageModel.ReceiverMessage){
            binding.itemmTitleTv.text = receiverMessage.message
        }

        companion object {
            fun create(parent: ViewGroup): ReceiverViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemAimessageBinding.inflate(layoutInflater, parent, false)
                return ReceiverViewHolder(view)
            }
        }
    }

    class SenderViewHolder(private val binding: ItemUsermessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(senderMessage: MessageModel.SenderMessage) {
            binding.itemusermTitleTv.text = senderMessage.message
        }

        companion object {
            fun create(parent: ViewGroup): SenderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemUsermessageBinding.inflate(layoutInflater, parent, false)
                return SenderViewHolder(view)
            }
        }

    }

}