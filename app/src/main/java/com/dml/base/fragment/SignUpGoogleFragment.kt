package com.dml.base.fragment

import android.os.Bundle
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
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
        emailET?.setText((activity as SignUpActivity).getUserSignUpRequestModel()?.user?.email)

        signUpBtn?.apply {
            setText(R.string.activity_signup_button_sign_up)
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