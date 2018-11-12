package com.dml.base.view.ui

import android.content.Intent
import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseActivity
import com.dml.base.view.ui.login.LoginActivity
import com.dml.base.view.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        GeneralMessageDialog().builder()
//                .btnText("test title")
//                .btnListener(DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
//                    Toast.makeText(this@WelcomeActivity, " clicked", Toast.LENGTH_SHORT).show()
//                })
//                .build()
//                .show(supportFragmentManager, "")
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun connectViews() {
        loginButton?.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.enter_from_down, R.anim.keep_stay)
        }

        signUpButton?.apply {
            setText(R.string.welcome_button_sign_up)
            setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, SignUpActivity::class.java))
            }
        }
    }
}