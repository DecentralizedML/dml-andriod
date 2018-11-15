package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import kotlinx.android.synthetic.main.item_security_question.view.*

class SecurityQuestionAdapter(var context: Context
                              , private val questionList: ArrayList<String>
                              , private val listener: OnItemClickListener?) : RecyclerView.Adapter<SecurityQuestionAdapter.ViewHolder>() {

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(question: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, questionList[position])

        holder.itemView.setOnClickListener {
            listener?.onClick(questionList[position])
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_security_question, parent, false))
    }

    override fun getItemCount() = questionList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, question: String) {
            itemView.questionTextView.text = question
        }
    }
}