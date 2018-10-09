package com.dml.base.ui

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent


class NoSwipeViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    /**
     * Is swipe enabled
     */
    private var enable: Boolean = true

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.enable) super.onTouchEvent(event) else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.enable) super.onInterceptTouchEvent(event) else false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return if (this.enable) super.executeKeyEvent(event) else false
    }

    fun setSwipeEnabled(enabled: Boolean) {
        this.enable = enabled
    }
}
