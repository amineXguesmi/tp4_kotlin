package com.example.tp5.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.R
import com.example.tp5.core.database.models.Schedule
import java.text.SimpleDateFormat
import java.util.*

class BusStopAdapter(private var scheduleList: List<Schedule>, private val onItemClick: (Schedule) -> Unit) :
    RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopName: TextView = itemView.findViewById(R.id.stopNameTextView)
        val arrivalTime: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bus_stop, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = scheduleList[position]
        holder.stopName.text = currentItem.stop_name
        holder.arrivalTime.text = currentItem.arrival_time.toLong().toTimeDateString();
        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
    fun updateList(newList: List<Schedule>) {
        scheduleList = newList
        notifyDataSetChanged()
    }
     private fun Long.toTimeDateString(): String {
        val dateTime = java.util.Date(this)
        val format = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.UK)
        return format.format(dateTime)
    }
}