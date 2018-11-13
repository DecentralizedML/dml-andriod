package com.dml.base.view.ui.settings.profile

import android.os.Bundle
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings_profile.*

class SettingsProfileFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsProfileFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings_profile
    }

    override fun connectViews() {

        toolbar?.apply {
            setTitle(R.string.settings)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
        }
    }


}