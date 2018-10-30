package com.dml.base.view.ui.fragment.signup

import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.view.activity.SignUpActivity
import kotlinx.android.synthetic.main.fragment_signup_google.*

class SignUpGoogleFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpGoogleFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_google
    }

    override fun connectViews() {
        emailEditText?.setText((mParentActivity as SignUpActivity).getUserSignUpRequestModel()?.user?.email)

        signUpButton?.apply {
            setText(R.string.activity_signup_button_sign_up)
            setOnClickListener { signUpByGoogle() }
        }
    }

    private fun signUpByGoogle() {
        if (!agreeCheckBox.isChecked)
            return

        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Complete)
    }
}