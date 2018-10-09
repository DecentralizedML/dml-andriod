package com.dml.base.fragment

import android.os.Bundle
import com.dml.base.R
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import com.dml.base.utility.DateInputMask
import kotlinx.android.synthetic.main.fragment_signup_second.*


class SignUpSecondFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpSecondFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_second
    }

    override fun connectViews() {
        nextBtn?.setOnClickListener { signUp() }

        dateOfBirthET?.let {
            DateInputMask(dateOfBirthET).listen()
        }
    }

    private fun signUp() {
//        if (!Utils.isValidEmail(emailET?.text.toString()))
//            Toast.makeText(activity, "valid", Toast.LENGTH_SHORT).show()
//
        if (activity is SignUpActivity) {
            (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Third)
        }
    }
}