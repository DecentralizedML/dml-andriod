package com.dml.base.view.ui.settings.datasource

import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings_data_source.*

class SettingsDataSourceFragment : BaseFragment(), SettingsDataSourceContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsDataSourceFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SettingsDataSourceContract.Presenter

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings_data_source
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.settings_data_sources)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
        }
    }

    override fun setPresenter(presenter: SettingsDataSourceContract.Presenter) {
        this.presenter = presenter
    }
}
