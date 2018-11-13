package com.dml.base.view.ui.cashout

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity

class CashOutActivity : BaseFragmentActivity() {
    override fun createBaseFragment() {
        startFragment(CashOutFragment.newInstance(null), CashOutFragment::class.java.simpleName, false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_cashout
    }

    override fun connectViews() {
    }
}