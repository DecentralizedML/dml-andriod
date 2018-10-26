package com.dml.base.ui

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
        leftBtn?.visibility = View.VISIBLE
        leftBtn?.setOnClickListener(onClickListener)
    }

    fun setLeftButton(id: Int, onClickListener: OnClickListener?) {
        this.setLeftButton(onClickListener)
        leftBtn?.setImageResource(id)
    }

    fun setRightButton(onClickListener: OnClickListener?) {
        rightBtn?.visibility = View.VISIBLE
        rightBtn?.setOnClickListener(onClickListener)
    }

    fun setRightButton(id: Int, onClickListener: OnClickListener?) {
        this.setRightButton(onClickListener)
        rightBtn?.setImageResource(id)
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
        walletBtn?.visibility = View.VISIBLE
        walletBtn?.setOnClickListener(onClickListener)
    }
}