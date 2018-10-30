package com.dml.base.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.dml.base.R
import kotlinx.android.synthetic.main.layout_toolbar_large.view.*

class LargeToolbar : RelativeLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar_large, this, true)
    }

    fun setLeftButton(onClickListener: OnClickListener?) {
        leftButton?.visibility = View.VISIBLE
        leftButton?.setOnClickListener(onClickListener)
    }

    fun setLeftButton(id: Int, onClickListener: OnClickListener?) {
        this.setLeftButton(onClickListener)
        leftButton?.setImageResource(id)
    }

    fun setRightButton(onClickListener: OnClickListener?) {
        rightButton?.visibility = View.VISIBLE
        rightButton?.setOnClickListener(onClickListener)
    }

    fun setRightButton(id: Int, onClickListener: OnClickListener?) {
        this.setRightButton(onClickListener)
        rightButton?.setImageResource(id)
    }

    fun setTitle(title: String) {
        titleTextView?.text = title
        titleTextView?.visibility = View.VISIBLE
    }

    fun setTitle(title: Int) {
        titleTextView?.text = context.getString(title)
        titleTextView?.visibility = View.VISIBLE
    }

    fun setWalletButton(onClickListener: OnClickListener?) {
        walletButton?.visibility = View.VISIBLE
        walletButton?.setOnClickListener(onClickListener)
    }
}