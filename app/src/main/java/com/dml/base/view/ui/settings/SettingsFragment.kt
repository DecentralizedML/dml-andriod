package com.dml.base.view.ui.settings

import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.view.ui.settings.currency.SettingsCurrencyFragment
import com.dml.base.view.ui.settings.datasource.SettingsDataSourceFragment
import com.dml.base.view.ui.settings.job.SettingsJobFragment
import com.dml.base.view.ui.settings.profile.SettingsProfileFragment
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings
    }

    override fun connectViews() {

        toolbar?.apply {
            setTitle(R.string.settings)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
        }

        jobsButton?.setOnClickListener {
            startFragment(SettingsJobFragment.newInstance(null), SettingsJobFragment::class.java.simpleName, true)
        }

        profileButton?.setOnClickListener {
            startFragment(SettingsProfileFragment.newInstance(null), SettingsProfileFragment::class.java.simpleName, true)
        }

        dataSourcesButton?.setOnClickListener {
            startFragment(SettingsDataSourceFragment.newInstance(null), SettingsDataSourceFragment::class.java.simpleName, true)
        }

        currencyButton?.setOnClickListener {
            startFragment(SettingsCurrencyFragment.newInstance(null), SettingsCurrencyFragment::class.java.simpleName, true)
        }

        logoutButton?.setOnClickListener {
            Utility.logout(context)
        }
    }
}