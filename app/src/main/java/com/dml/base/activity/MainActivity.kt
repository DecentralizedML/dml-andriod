package com.dml.base.activity

import android.content.Intent
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.current_balance)
            setRightButton(R.drawable.ic_action_settings, View.OnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))

            })
            setWalletButton(View.OnClickListener {
                startActivity(Intent(this@MainActivity, WalletActivity::class.java))
            })
        }
    }
}