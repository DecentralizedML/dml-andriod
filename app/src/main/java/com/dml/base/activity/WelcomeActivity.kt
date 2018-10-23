package com.dml.base.activity

import android.content.Intent
import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseActivity
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
        signUpBtn?.apply {
            setText(R.string.welcome_button_sign_up)
            setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, SignUpActivity::class.java))
                finish()
            }
        }

        loginBtn?.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            finish()
        }
    }
}