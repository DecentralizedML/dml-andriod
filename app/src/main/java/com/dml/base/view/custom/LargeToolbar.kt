package com.dml.base.view.custom

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader.TileMode
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import com.dml.base.R
import kotlinx.android.synthetic.main.layout_toolbar_large.view.*


class LargeToolbar : RelativeLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar_large, this, true)

        amountTextView?.apply {
            val shader = LinearGradient(0f, 0f, amountTextView.textSize, 0f,
                    intArrayOf(ResourcesCompat.getColor(resources, R.color.text_gradient_start, null)
                            , ResourcesCompat.getColor(resources, R.color.text_gradient_end, null)),
                    floatArrayOf(0f, 1f), TileMode.CLAMP)
            amountTextView.paint.shader = shader
        }
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

//    fun setWalletButton(onClickListener: OnClickListener?) {
//        walletButton?.visibility = View.VISIBLE
//        walletButton?.setOnClickListener(onClickListener)
//    }
}