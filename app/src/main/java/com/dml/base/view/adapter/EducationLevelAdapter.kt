package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import kotlinx.android.synthetic.main.item_education_level.view.*

class EducationLevelAdapter(var context: Context, val listener: OnItemClickListener?) : RecyclerView.Adapter<EducationLevelAdapter.ViewHolder>() {

    private val levelList: Array<String> = context.resources.getStringArray(R.array.array_education_level)
    private var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(title: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, levelList[position])
        holder.itemView.setOnClickListener {
            listener?.onClick(levelList[position])
            selectedPosition = position
            notifyDataSetChanged()
        }

        if (selectedPosition == position) {
            holder.itemView.titleTextView.setBackgroundResource(R.drawable.bg_circle_filled)
        } else {
            holder.itemView.titleTextView.setBackgroundResource(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_education_level, parent, false))
    }

    override fun getItemCount() = levelList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, title: String) {
            itemView.titleTextView.text = title
        }
    }
}