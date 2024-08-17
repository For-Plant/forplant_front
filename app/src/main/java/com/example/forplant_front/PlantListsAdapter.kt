package com.example.forplant_front

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.connection.RetrofitClient2

class PlantListsAdapter(private val itemList: List<RetrofitClient2.Plant>) : RecyclerView.Adapter<PlantListsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plantlists, parent, false)
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

        fun bind(item: RetrofitClient2.Plant) {
            textView.text = item.nickname
            textView2.text = item.name
        }
    }
}