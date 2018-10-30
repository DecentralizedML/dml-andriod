package com.dml.base.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemVecticalDecoration(var space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            top = space
            bottom = space
            left = 0
            right = 0

            if (parent.getChildAdapterPosition(view) == 0)
                top = 0

            if (parent.getChildAdapterPosition(view) - 1 == parent.adapter?.itemCount)
                bottom = 0
        }
    }
}
