package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.network.model.JobResponse


class JobAdapter(var context: Context, jobList: ArrayList<JobResponse>, val listener: OnItemClickListener?) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    val jobList = jobList

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(title: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, jobList[position])
//        holder.itemView.setOnClickListener {
//            listener?.onClick(jobList[position])
//            selectedPosition = position
//            notifyDataSetChanged()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false))
    }

    override fun getItemCount() = jobList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, response: JobResponse) {
//            itemView.titleTextView.text = response.name
//            itemView.descTextView.text = response.date
        }
    }
}