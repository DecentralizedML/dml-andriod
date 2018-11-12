package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.network.model.TransactionResponse

class TransactionAdapter(var context: Context
                         , private val transactionList: ArrayList<TransactionResponse>
                         , private val listener: OnItemClickListener?) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(title: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, transactionList[position])

//        holder.itemView.setOnClickListener {
//            listener?.onClick(transactionList[position])
//            selectedPosition = position
//            notifyDataSetChanged()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount() = transactionList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, response: TransactionResponse) {
//            itemView.titleTextView.text = response.name
//            itemView.descTextView.text = response.date
        }
    }
}