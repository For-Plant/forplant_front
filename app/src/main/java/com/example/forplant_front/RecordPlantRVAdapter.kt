package com.example.forplant_front

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordPlantRVAdapter(private var recordlist: MutableList<String>) : RecyclerView.Adapter<RecordPlantRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recordlist[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return recordlist.size
    }

    fun updateData(newList: List<String>) {
        recordlist.clear()
        recordlist.addAll(newList)
        notifyDataSetChanged() // 데이터 변경을 알리고 리사이클러뷰를 업데이트함
    }

    fun removeItem(position: Int) {
        recordlist.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, recordlist.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date: TextView = itemView.findViewById(R.id.item_plantname_tv)

        fun bind(item: String) {
            date.text = item

            // 아이템 클릭 이벤트 설정
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, RecordViewActivity::class.java)
                intent.putExtra("DATE", item)  // 선택된 일자의 데이터를 넘겨줌
                intent.putExtra("POSITION", position)  // 아이템의 위치를 넘겨줌
                context.startActivity(intent)
            }
        }
    }
}