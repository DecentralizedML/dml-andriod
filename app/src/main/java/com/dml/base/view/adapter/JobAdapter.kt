package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.network.model.JobResponse
import kotlinx.android.synthetic.main.item_job.view.*

class JobAdapter(var context: Context
                 , private val itemWidth: Int
                 , private val jobList: ArrayList<JobResponse>
                 , private val listener: OnItemClickListener?) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(title: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, jobList[position])

        val layoutParam = holder.itemView.itemCardView.layoutParams
        layoutParam.width = itemWidth
        holder.itemView.itemCardView.layoutParams = layoutParam

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