package com.example.forplant_front

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RipRecordAdapter(private val fragment: RipBottomSheet) : RecyclerView.Adapter<RipRecordAdapter.ViewHolder>() {

    private val itemList = listOf("아이템 1", "아이템 2", "아이템 3", "아이템 4", "아이템 5", "아이템 6")

    init {
        Log.d("RipRecordAdapter", "ItemList count: ${itemList.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recordlists, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        Log.d("RipRecordAdapter", "Binding item at position $position: $item")
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView57)

        fun bind(item: String) {
            textView.text = item

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, RipLIstOnedayActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}