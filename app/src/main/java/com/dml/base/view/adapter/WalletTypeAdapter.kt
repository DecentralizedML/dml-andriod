package com.dml.base.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.network.model.WalletTypeResponse
import kotlinx.android.synthetic.main.item_wallet_type.view.*

class WalletTypeAdapter(var context: Context
                        , private val walletTypeList: ArrayList<WalletTypeResponse>
                        , private val listener: OnItemClickListener?) : RecyclerView.Adapter<WalletTypeAdapter.ViewHolder>() {

    var selectedPosition = -1

    interface OnItemClickListener {
        fun onClick(response: WalletTypeResponse)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(context, walletTypeList[position])

        holder.itemView.setOnClickListener {
            listener?.onClick(walletTypeList[position])
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wallet_type, parent, false))
    }

    override fun getItemCount() = walletTypeList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(context: Context, response: WalletTypeResponse) {
            itemView.titleTextView.text = response.name
//            itemView.descTextView.text = response.date

            when (response.type) {
                "dml" -> itemView.iconImageView.setImageResource(R.drawable.ic_dml)
                "eth" -> itemView.iconImageView.setImageResource(R.drawable.ic_eth)
            }

        }
    }
}