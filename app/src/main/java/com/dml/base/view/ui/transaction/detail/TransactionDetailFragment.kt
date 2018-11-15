package com.dml.base.view.ui.transaction.detail

import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_transaction_detail.*

class TransactionDetailFragment : BaseFragment(), TransactionDetailContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = TransactionDetailFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: TransactionDetailContract.Presenter

    private var oldScrollY = 0
    private var isHiding = false

    override fun setLayoutId(): Int {
        return R.layout.fragment_transaction_detail
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.fragment_transaction_detail_toolbar_title)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
        }
//
//        if (cancelPendingButton.visibility == View.VISIBLE) {
//            if (!Utility.canScroll(contentScrollView)) {
//                val layoutParam = cancelPendingButton.layoutParams
//                val marginBottom = layoutParam.height + Utility.convertDpToPixel(16f * 2)
//
//                val updateLayoutParam = contentLayout.layoutParams as FrameLayout.LayoutParams
//                updateLayoutParam.bottomMargin = marginBottom.toInt()
//            }
//
//            contentScrollView?.viewTreeObserver?.addOnScrollChangedListener {
//                contentScrollView?.let {
//                    val scrollY = contentScrollView.scrollY
//
//                    if (Utility.canScroll(contentScrollView)) {
//                        if (Math.abs(scrollY - oldScrollY) > 20) {
//                            if (scrollY > oldScrollY) {
//                                if (!isHiding) {
//                                    val transAnimation = ObjectAnimator.ofFloat(0f, 300f)
//                                    transAnimation.addUpdateListener {
//                                        val value = it.animatedValue as Float
//                                        cancelPendingButton.translationY = value
//                                    }
//                                    transAnimation.interpolator = LinearInterpolator()
//                                    transAnimation.duration = 200
//                                    transAnimation.start()
//
//                                    isHiding = true
//                                }
//                            } else {
//                                if (isHiding) {
//                                    val transAnimation = ObjectAnimator.ofFloat(300f, 0f)
//                                    transAnimation.addUpdateListener {
//                                        val value = it.animatedValue as Float
//                                        cancelPendingButton.translationY = value
//                                    }
//                                    transAnimation.interpolator = LinearInterpolator()
//                                    transAnimation.duration = 200
//                                    transAnimation.start()
//
//                                    isHiding = false
//                                }
//                            }
//                        }
//                        oldScrollY = scrollY
//                    }
//                }
//            }
//        }
//
//        cancelPendingButton?.apply {
//            setText(R.string.fragment_transaction_detail_button_cancel_pending_transaction)
//            setOnClickListener {
//            }
//        }
    }

    override fun setPresenter(presenter: TransactionDetailContract.Presenter) {
        this.presenter = presenter
    }


}