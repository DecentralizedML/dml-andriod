package com.dml.base.view.ui.settings.job

import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings_profile.*

class SettingsJobFragment : BaseFragment(), SettingsJobContract.View {
    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsJobFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SettingsJobContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SettingsJobPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings_job
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.settings_jobs)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity.onBackPressed()
            })
        }
    }

    override fun setPresenter(presenter: SettingsJobContract.Presenter) {
        this.presenter = presenter
    }
}