package com.dml.base.view.ui.wallet.transaction

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_transaction_detail.*


class TransactionDetailActivity : BaseActivity() {
    var oldScrollY = 0
    var isHiding = false

    override fun setLayoutId(): Int {
        return R.layout.activity_transaction_detail
    }

    override fun connectViews() {
        toolbar?.setTitle(R.string.activity_transaction_detail_toolbar_title)

        contentScrollView?.viewTreeObserver?.addOnScrollChangedListener {
            val scrollY = contentScrollView.scrollY

            if (Utility.canScroll(contentScrollView)) {
                if (Math.abs(scrollY - oldScrollY) > 20) {
                    if (scrollY > oldScrollY) {
                        if (!isHiding) {
                            val transAnimation = ObjectAnimator.ofFloat(0f, 300f)
                            transAnimation.addUpdateListener {
                                val value = it.animatedValue as Float
                                signUpButton.translationY = value
                            }
                            transAnimation.interpolator = LinearInterpolator()
                            transAnimation.duration = 200
                            transAnimation.start()

                            isHiding = true
                        }
                    } else {
                        if (isHiding) {
                            val transAnimation = ObjectAnimator.ofFloat(300f, 0f)
                            transAnimation.addUpdateListener {
                                val value = it.animatedValue as Float
                                signUpButton.translationY = value
                            }
                            transAnimation.interpolator = LinearInterpolator()
                            transAnimation.duration = 200
                            transAnimation.start()

                            isHiding = false
                        }
                    }
                }
                oldScrollY = scrollY
            }

//            scrollY?.let {
//                if (scrollY > 0
//                        && scrollY < contentScrollView?.getChildAt(0)!!.height
//                        && Math.abs(scrollY - oldScrollY) > 10) {
//                    Log.v("Scroll", "oldScrollY: $oldScrollY scrollY: $scrollY")
//
//                    if (scrollY > oldScrollY) {
//                        if (toolbar?.height!! > Utility.convertDpToPixel(this@TransactionDetailActivity, 58f)) {
////                            toolbar?.layoutParams = LinearLayout.LayoutParams(toolbar.width, toolbar.height - 10)
//                            toolbar?.translationY = toolbar.height - 10f
//                        }
//                    } else {
//                        if (toolbar?.height!! < Utility.convertDpToPixel(this@TransactionDetailActivity, 170f)) {
////                            toolbar?.layoutParams = LinearLayout.LayoutParams(toolbar.width, toolbar.height + 10)
//                            toolbar?.translationY = toolbar.height + 10f
//                        }
//                    }
//                    oldScrollY = scrollY
//                }
//            }
        }
    }
}