package com.example.falabellatest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.falabellatest.data.Data
import com.example.falabellatest.R

class DataAdapter(val listener: (Data) -> Unit) : RecyclerView.Adapter<DataAdapter.DataHolder>() {
    private var data: List<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.data_item, parent, false)
        return DataHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val currentData = data[position]
        holder.name.text = currentData.name
        holder.value.text = currentData.value
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<Data>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var value: TextView = itemView.findViewById(R.id.value)
        fun bind(data: Data) {
            itemView.setOnClickListener { listener(data) }
        }
    }
}