package com.example.forplant_front

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordRVAdapter(private val plantList: List<String>) : RecyclerView.Adapter<RecordRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = plantList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val plantName: TextView = itemView.findViewById(R.id.item_plantname_tv)

        fun bind(item: String) {
            plantName.text = item

            // 아이템 클릭 이벤트 설정
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, RecordPlantActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}