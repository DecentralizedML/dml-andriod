package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import kotlinx.android.synthetic.main.item_seed_phrase.view.*

class SeedPhraseAdapter(var context: Context
                        , private val textList: Array<String>
                        , private val listener: OnItemClickListener?) : RecyclerView.Adapter<SeedPhraseAdapter.ViewHolder>() {

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(text: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, textList[position], position)

        holder.itemView.setOnClickListener {
            listener?.onClick(textList[position])
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_seed_phrase, parent, false))
    }

    override fun getItemCount() = textList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, text: String, position: Int) {
            itemView.orderTextView.text = (position + 1).toString()
            itemView.phraseTextView.text = text
        }
    }
}