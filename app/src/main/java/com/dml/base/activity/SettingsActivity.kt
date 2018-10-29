package com.dml.base.activity

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity
import com.dml.base.fragment.settings.SettingsFragment

class SettingsActivity : BaseFragmentActivity() {

    override fun createBaseFragment() {
        startFragment(SettingsFragment.newInstance(null), false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_settings
    }

    override fun connectViews() {
    }
}