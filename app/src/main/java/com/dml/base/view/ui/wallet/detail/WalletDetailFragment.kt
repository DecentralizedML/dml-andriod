package com.dml.base.view.ui.wallet.detail

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.network.model.TransactionResponse
import com.dml.base.utils.MarginItemVecticalDecoration
import com.dml.base.view.adapter.TransactionAdapter
import com.dml.base.view.ui.settings.SettingsActivity
import com.dml.base.view.ui.transaction.TransactionActivity
import kotlinx.android.synthetic.main.fragment_wallet_detail.*

class WalletDetailFragment : BaseFragment(), WalletDetailContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = WalletDetailFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: WalletDetailContract.Presenter

    private var oldScrollY = 0
    private var isHiding = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = WalletDetailPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_wallet_detail
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.current_balance)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity.onBackPressed()
            })
            setRightButton(R.drawable.ic_action_settings, View.OnClickListener {
                startActivity(Intent(mParentActivity, SettingsActivity::class.java))
            })
        }

        contentScrollView?.viewTreeObserver?.addOnScrollChangedListener {
            contentScrollView?.let {
                val scrollY = contentScrollView.scrollY

                if (Utility.canScroll(contentScrollView)) {
                    if (Math.abs(scrollY - oldScrollY) > 20) {
                        if (scrollY > oldScrollY) {
                            if (!isHiding) {
                                val transAnimation = ObjectAnimator.ofFloat(0f, 300f)
                                transAnimation.addUpdateListener {
                                    val value = it.animatedValue as Float
                                    cashOutButton.translationY = value
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
                                    cashOutButton.translationY = value
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
            }
        }

        cashOutButton?.apply {
            setText(R.string.fragment_wallet_button_cash_out)
            setOnClickListener {
            }
        }

        setTransactionAdapter()
    }

    override fun setPresenter(presenter: WalletDetailContract.Presenter) {
        this.presenter = presenter
    }

    private fun setTransactionAdapter() {
        val transactionList = ArrayList<TransactionResponse>()
        transactionList.add(TransactionResponse())
        transactionList.add(TransactionResponse())
        transactionList.add(TransactionResponse())

        val adapter = TransactionAdapter(context, transactionList, object : TransactionAdapter.OnItemClickListener {
            override fun onClick(response: TransactionResponse) {
                val intent = Intent(mParentActivity, TransactionActivity::class.java)
                intent.putExtra("redirect", "TransactionDetailFragment")
                mParentActivity.startActivity(intent)
            }
        })
        transactionRecyclerView?.adapter = adapter
        transactionRecyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        transactionRecyclerView?.addItemDecoration(MarginItemVecticalDecoration(resources.getDimension(R.dimen.margin_transaction).toInt()))
    }

    override fun showProgressBar() {
    }

    override fun dismissProgressBar() {
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}