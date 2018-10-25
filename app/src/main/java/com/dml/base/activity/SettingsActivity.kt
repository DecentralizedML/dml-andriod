package com.dml.base.activity

import android.view.View
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_settings
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.settings)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener { finish() })
        }

        logoutBtn?.setOnClickListener {
            Utility.logout(this@SettingsActivity)
        }
    }
}