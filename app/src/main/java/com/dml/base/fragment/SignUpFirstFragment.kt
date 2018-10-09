package com.dml.base.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.dml.base.R
import com.dml.base.Utils
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_first.*

class SignUpFirstFragment : BaseFragment() {

    var showPassword = false

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpFirstFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_first
    }

    override fun connectViews() {
        passwordET?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val DRAWABLE_LEFT = 0
                val DRAWABLE_TOP = 1
                val DRAWABLE_RIGHT = 2
                val DRAWABLE_BOTTOM = 3

                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= passwordET.getRight() - passwordET.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                        if (showPassword) {
                            showPassword = false
                            passwordET?.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        } else {
                            showPassword = true
                            passwordET?.transformationMethod = PasswordTransformationMethod.getInstance()
                        }

                        return true
                    }
                }
                return false
            }
        })

        signUpBtn?.setOnClickListener { signUp() }
    }

    private fun signUp() {
        if (Utils.isValidEmail(emailET?.text.toString()))
            Toast.makeText(activity, "valid", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(activity, "invalid", Toast.LENGTH_SHORT).show()

//        if (activity is SignUpActivity) {
//            (activity as SignUpActivity).setState(SignUpState.Second)
//        }
    }
}