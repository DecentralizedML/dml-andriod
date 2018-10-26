package com.dml.base.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.dml.base.R
import kotlinx.android.synthetic.main.layout_button.view.*

class CustomThemeButton : RelativeLayout {
    private var text = ""
    private var showRightIcon = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_button, this, true)
    }

    fun setText(id: Int) {
        text = context.getString(id)
        btnTextView?.text = text
    }

    fun setTextString(text: String) {
        this.text = text
        btnTextView?.text = text
    }

    fun showRightIcon(show: Boolean) {
        showRightIcon = show

        if (show)
            rightImageView?.visibility = View.VISIBLE
        else
            rightImageView?.visibility = View.GONE
    }

    fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
            btnTextView?.visibility = View.GONE
            rightImageView?.visibility = View.GONE
        } else {
            progressBar?.visibility = View.GONE
            btnTextView?.visibility = View.VISIBLE

            if (showRightIcon)
                rightImageView?.visibility = View.VISIBLE
            else
                rightImageView?.visibility = View.GONE
        }
    }

}