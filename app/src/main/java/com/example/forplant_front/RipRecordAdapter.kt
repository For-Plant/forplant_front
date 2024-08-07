package com.example.forplant_front

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RipRecordAdapter(private val itemList: List<String>) : RecyclerView.Adapter<RipRecordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recordlists, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView57)

        fun bind(item: String) {
            textView.text = item

            // 아이템 클릭 이벤트 설정
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, RipLIstOnedayActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}
