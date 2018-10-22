package com.dml.base.utility

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemHorizontalDecoration(var space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            top = 0
            bottom = 0
            left = space
            right = space

            if (parent.getChildAdapterPosition(view) == 0)
                left = 0

            if (parent.getChildAdapterPosition(view) - 1 == parent.adapter?.itemCount)
                right = 0
        }
    }
}
