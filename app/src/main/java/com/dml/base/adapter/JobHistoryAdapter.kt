package com.dml.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.model.JobHistoryModel
import kotlinx.android.synthetic.main.item_job_history.view.*

class JobHistoryAdapter(var context: Context, jobHistoryList: ArrayList<JobHistoryModel>, val listener: OnItemClickListener?) : RecyclerView.Adapter<JobHistoryAdapter.ViewHolder>() {

    val jobHistoryList = jobHistoryList

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(title: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, jobHistoryList[position])
//        holder.itemView.setOnClickListener {
//            listener?.onClick(jobHistoryList[position])
//            selectedPosition = position
//            notifyDataSetChanged()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_job_history, parent, false))
    }

    override fun getItemCount() = jobHistoryList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, model: JobHistoryModel) {
            itemView.nameTextView.text = model.name
            itemView.dateTextView.text = model.date
            itemView.amountTextView.text = model.reward
            itemView.rateTextView.text = "\$0.05 USD"
        }
    }
}