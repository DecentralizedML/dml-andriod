package com.dml.base.view.ui.settings.currency

import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings_currency.*

class SettingsCurrencyFragment : BaseFragment(), SettingsCurrencyContract.View {
    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsCurrencyFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SettingsCurrencyContract.Presenter

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings_currency
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.settings_currency)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
        }
    }

    override fun setPresenter(presenter: SettingsCurrencyContract.Presenter) {
        this.presenter = presenter
    }
}