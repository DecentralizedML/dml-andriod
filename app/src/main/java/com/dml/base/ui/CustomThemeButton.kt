package com.dml.base.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.dml.base.R
import kotlinx.android.synthetic.main.layout_button.view.*

class CustomThemeButton : RelativeLayout {
    var text = ""
    var showRightIcon = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_button, this, true)
    }

    fun setText(id: Int) {
        text = context.getString(id)
        btnTV?.text = text
    }

    fun showRightIcon(show: Boolean) {
        showRightIcon = show

        if (show)
            rightIV?.visibility = View.VISIBLE
        else
            rightIV?.visibility = View.GONE
    }

    fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
            btnTV?.visibility = View.GONE
            rightIV?.visibility = View.GONE
        } else {
            progressBar?.visibility = View.GONE
            btnTV?.visibility = View.VISIBLE

            if (showRightIcon)
                rightIV?.visibility = View.VISIBLE
            else
                rightIV?.visibility = View.GONE
        }
    }

}