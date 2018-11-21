package com.dml.base.view.ui.wallet

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.network.model.TransactionResponse
import com.dml.base.network.model.WalletTypeResponse
import com.dml.base.utils.MarginItemVecticalDecoration
import com.dml.base.view.adapter.TransactionAdapter
import com.dml.base.view.adapter.WalletTypeAdapter
import com.dml.base.view.custom.CenterZoomLayoutManager
import com.dml.base.view.ui.cashout.CashOutActivity
import com.dml.base.view.ui.settings.SettingsActivity
import com.dml.base.view.ui.transaction.TransactionActivity
import com.dml.base.view.ui.wallet.detail.WalletDetailFragment
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.fragment_wallet.*

class WalletFragment : BaseFragment(), WalletContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = WalletFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: WalletContract.Presenter

    private var oldScrollY = 0
    private var isHiding = false

    override fun setLayoutId(): Int {
        return R.layout.fragment_wallet
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.current_balance)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
            setRightButton(R.drawable.ic_action_settings, View.OnClickListener {
                startActivity(Intent(mParentActivity, SettingsActivity::class.java))
            })
        }

        recentTransactionsButton?.setOnClickListener {
            mParentActivity?.startActivity(Intent(mParentActivity, TransactionActivity::class.java))
        }

        cashOutButton?.apply {
            setText(R.string.fragment_wallet_button_cash_out)
            setOnClickListener {
                mParentActivity?.startActivity(Intent(mParentActivity, CashOutActivity::class.java))
            }
        }

        if (!Utility.canScroll(contentScrollView)) {
            val layoutParam = cashOutButton.layoutParams
            val marginBottom = layoutParam.height + Utility.convertDpToPixel(16f * 2)

            val updateLayoutParam = contentLayout.layoutParams as FrameLayout.LayoutParams
            updateLayoutParam.bottomMargin = marginBottom.toInt()
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

        setWalletTypeAdapter()
        setTransactionAdapter()
    }

    override fun setPresenter(presenter: WalletContract.Presenter) {
        this.presenter = presenter
    }

    private fun setWalletTypeAdapter() {
        val walletTypeList = ArrayList<WalletTypeResponse>()

        val eth = WalletTypeResponse()
        eth.name = "Ethereum"
        eth.type = "eth"
        walletTypeList.add(WalletTypeResponse())
        walletTypeList.add(eth)

        val adapter = WalletTypeAdapter(context, walletTypeList, object : WalletTypeAdapter.OnItemClickListener {
            override fun onClick(response: WalletTypeResponse) {
                startFragment(WalletDetailFragment.newInstance(null), WalletDetailFragment::class.java.simpleName, true)
            }
        })
        walletTypeRecyclerView?.adapter = adapter
        val layoutManager = CenterZoomLayoutManager(context, RecyclerView.HORIZONTAL, false)
        walletTypeRecyclerView?.layoutManager = layoutManager
        GravitySnapHelper(Gravity.START).attachToRecyclerView(walletTypeRecyclerView)
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
                mParentActivity?.startActivity(intent)
            }
        })
        transactionRecyclerView?.adapter = adapter
        transactionRecyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        transactionRecyclerView?.addItemDecoration(MarginItemVecticalDecoration(resources.getDimension(R.dimen.margin_transaction).toInt()))
    }
}