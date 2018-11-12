package com.dml.base.view.ui.wallet

import android.content.Intent
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseActivity
import com.dml.base.view.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*

class WalletActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_wallet
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.current_balance)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener { finish() })
            setRightButton(R.drawable.ic_action_settings, View.OnClickListener {
                startActivity(Intent(this@WalletActivity, SettingsActivity::class.java))

            })
        }
    }
}