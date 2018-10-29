package com.dml.base.fragment.signup

import android.os.Bundle
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_complete.*

class SignUpCompleteFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpCompleteFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_complete
    }

    override fun connectViews() {
        checkOutBtn?.apply {
            setText(R.string.activity_signup_complete_button_check_out)
            showRightIcon(true)
            setOnClickListener { checkOut() }
        }
    }

    private fun checkOut() {

    }

    private fun signUp() {
        if (activity is SignUpActivity) {
            (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Complete)
        }
    }
}