package com.dml.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.model.JobModel


class JobAdapter(var context: Context, jobList: ArrayList<JobModel>, val listener: OnItemClickListener?) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

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
        fun bindData(context: Context, model: JobModel) {
//            itemView.titleTextView.text = model.name
//            itemView.descTextView.text = model.date
        }
    }
}