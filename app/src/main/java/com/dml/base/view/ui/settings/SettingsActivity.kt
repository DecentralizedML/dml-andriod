package com.dml.base.view.ui.settings

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity
import com.dml.base.view.ui.settings.SettingsFragment

class SettingsActivity : BaseFragmentActivity() {

    override fun createBaseFragment() {
        startFragment(SettingsFragment.newInstance(null), SettingsFragment::class.java.simpleName, false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_settings
    }

    override fun connectViews() {
    }
}