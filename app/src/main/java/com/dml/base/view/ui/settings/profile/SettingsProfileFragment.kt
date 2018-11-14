package com.dml.base.view.ui.settings.profile

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.utils.MarginItemHorizontalDecoration
import com.dml.base.view.adapter.EducationLevelAdapter
import kotlinx.android.synthetic.main.fragment_settings_profile.*

class SettingsProfileFragment : BaseFragment(), SettingsProfileContract.View {
    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SettingsProfileFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SettingsProfileContract.Presenter

    override fun setLayoutId(): Int {
        return R.layout.fragment_settings_profile
    }

    override fun connectViews() {
        toolbar?.apply {
            setTitle(R.string.settings_profile)
            setLeftButton(R.drawable.ic_action_back, View.OnClickListener {
                mParentActivity?.onBackPressed()
            })
        }

        updateButton?.apply {
            setText(R.string.settings_profile_button_update)
        }

        setEducationLevelAdapter()
    }

    override fun setPresenter(presenter: SettingsProfileContract.Presenter) {
        this.presenter = presenter
    }

    private fun setEducationLevelAdapter() {
        val adapter = EducationLevelAdapter(context, object : EducationLevelAdapter.OnItemClickListener {
            override fun onClick(title: String) {
//                Toast.makeText(context, "clicked item $title", Toast.LENGTH_SHORT).show()
            }
        })
        educationLevelRecycleView?.adapter = adapter
        educationLevelRecycleView?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        educationLevelRecycleView?.addItemDecoration(MarginItemHorizontalDecoration(resources.getDimension(R.dimen.margin_education_level).toInt()))
    }
}