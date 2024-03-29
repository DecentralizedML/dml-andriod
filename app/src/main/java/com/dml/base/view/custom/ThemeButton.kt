package com.dml.base.view.custom

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
            0 -> typeface = getFont(Font.BARLOW_REGULAR, context)
            1 -> typeface = getFont(Font.BARLOW_MEDIUM, context)
            2 -> typeface = getFont(Font.BARLOW_SEMIBOLD, context)
        }
        ta.recycle()
    }

    fun getFont(font: Font, context: Context): Typeface {
        return when (font) {
            Font.BARLOW_REGULAR -> Typeface.createFromAsset(context.assets, "fonts/Barlow-Regular.ttf")
            Font.BARLOW_MEDIUM -> Typeface.createFromAsset(context.assets, "fonts/Barlow-Medium.ttf")
            Font.BARLOW_SEMIBOLD -> Typeface.createFromAsset(context.assets, "fonts/Barlow-SemiBold.ttf")
        }
        return Typeface.DEFAULT
    }
}