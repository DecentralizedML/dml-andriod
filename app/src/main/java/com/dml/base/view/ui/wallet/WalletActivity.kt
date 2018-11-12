package com.dml.base.view.ui.wallet

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity

class WalletActivity : BaseFragmentActivity() {
    override fun connectViews() {
    }

    override fun createBaseFragment() {
        startFragment(WalletFragment.newInstance(null), false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_wallet
    }
}