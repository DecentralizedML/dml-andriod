package com.dml.base.ui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button
import com.dml.base.R

class ThemeButton : Button {
    enum class Font {
        BARLOW_REGULAR, BARLOW_MEDIUM, BARLOW_SEMIBOLD
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setFont(context, attrs)
    }

    fun setFont(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ThemeTextView, 0, 0)
        val value = ta.getInt(R.styleable.ThemeTextView_appFontStyle, 0)
        when (value) {
            0 -> typeface = getFont(ThemeButton.Font.BARLOW_REGULAR, context)
            1 -> typeface = getFont(ThemeButton.Font.BARLOW_MEDIUM, context)
            2 -> typeface = getFont(ThemeButton.Font.BARLOW_SEMIBOLD, context)
        }
        ta.recycle()
    }

    fun getFont(font: Font, context: Context): Typeface {
        return when (font) {
            ThemeButton.Font.BARLOW_REGULAR -> Typeface.createFromAsset(context.assets, "fonts/Barlow-Regular.ttf")
            ThemeButton.Font.BARLOW_MEDIUM -> Typeface.createFromAsset(context.assets, "fonts/Barlow-Medium.ttf")
            ThemeButton.Font.BARLOW_SEMIBOLD -> Typeface.createFromAsset(context.assets, "fonts/Barlow-SemiBold.ttf")
        }
        return Typeface.DEFAULT
    }
}