package com.example.forplant_front

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.connection.RetrofitClient2

class RipAdapter(private val itemList: List<RetrofitClient2.DeadPlant>) : RecyclerView.Adapter<RipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plantrip, parent, false)
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
        private val textView2: TextView = itemView.findViewById(R.id.textView58)
        private val textView3: TextView = itemView.findViewById(R.id.textView60)

        fun bind(item: RetrofitClient2.DeadPlant) {
            textView.text = item.nickname
            textView2.text = item.plantCreatedAt
            textView3.text = item.deadCreatedAt

            // 아이템 클릭 이벤트 설정
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, PlantRecordLIstRipActivity::class.java)
                intent.putExtra("plant_nickname", item.nickname)  // nickname을 Intent에 추가
                context.startActivity(intent)
            }
        }
    }
}