package com.dml.base.activity

import android.content.Intent
import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import android.support.constraint.solver.widgets.WidgetContainer.getBounds
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlinx.android.synthetic.main.fragment_signup_first.*


class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startBtn?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun createBaseFragment() {
    }

}