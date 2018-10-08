package com.dml.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import com.dml.base.R
import com.dml.base.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup_first.*

class SignupFirstFragment : BaseFragment() {

    var showPassword = false

    override fun newInstance(bundle: Bundle?): SignupFirstFragment {
        val fragment = Fragment() as SignupFirstFragment
        if (bundle != null)
            fragment.arguments = bundle
        return fragment
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
                            passwordET?.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                        } else {
                            showPassword = true
                            passwordET?.inputType = InputType.TYPE_CLASS_TEXT
                        }

                        return true
                    }
                }
                return false
            }
        })
    }
}