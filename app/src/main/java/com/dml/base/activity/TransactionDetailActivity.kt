package com.dml.base.activity

import android.util.Log
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_transaction_detail.*


class TransactionDetailActivity : BaseActivity() {
    var previousscrollY = 0

    override fun setLayoutId(): Int {
        return R.layout.activity_transaction_detail
    }

    override fun connectViews() {
        toolbar?.setTitle(R.string.activity_transaction_detail_toolbar_title)

        contentScrollView?.viewTreeObserver?.addOnScrollChangedListener {
            val scrollX = contentScrollView?.scrollX
            val scrollY = contentScrollView?.scrollY

            scrollY?.let {
                if (scrollY > 0
                        && scrollY < contentScrollView?.getChildAt(0)!!.height
                        && Math.abs(scrollY - previousscrollY) > 10) {
                    Log.v("Scroll", "previousscrollY: $previousscrollY scrollY: $scrollY")

                    if (scrollY > previousscrollY) {
                        if (toolbar?.height!! > Utility.convertDpToPixel(this@TransactionDetailActivity, 58f)) {
//                            toolbar?.layoutParams = LinearLayout.LayoutParams(toolbar.width, toolbar.height - 10)
                            toolbar?.translationY = toolbar.height - 10f
                        }
                    } else {
                        if (toolbar?.height!! < Utility.convertDpToPixel(this@TransactionDetailActivity, 170f)) {
//                            toolbar?.layoutParams = LinearLayout.LayoutParams(toolbar.width, toolbar.height + 10)
                            toolbar?.translationY = toolbar.height + 10f
                        }
                    }
                    previousscrollY = scrollY
                }
            }
        }
    }
}