package com.dml.base.view.ui.transaction

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity
import com.dml.base.view.ui.transaction.detail.TransactionDetailFragment


class TransactionActivity : BaseFragmentActivity() {
    override fun createBaseFragment() {
        val redirect = intent.getStringExtra("redirect")

        if (redirect == null) {
            startFragment(TransactionFragment.newInstance(null), TransactionFragment::class.java.simpleName, false)
        } else {
            when (redirect) {
                "TransactionDetailFragment" -> startFragment(TransactionDetailFragment.newInstance(null), TransactionFragment::class.java.simpleName, false)
            }

        }
    }

    override fun connectViews() {
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_transaction
    }

}