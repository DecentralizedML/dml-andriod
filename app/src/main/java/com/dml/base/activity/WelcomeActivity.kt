package com.dml.base.activity

import android.content.Intent
import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun connectViews() {
        startBtn?.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SignUpActivity::class.java))
            finish()
        }
    }
}