package com.dml.base.view.ui.login

import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity
import com.dml.base.view.ui.login.LoginFragment

class LoginActivity : BaseFragmentActivity() {
    override fun createBaseFragment() {
        startFragment(LoginFragment.newInstance(null), LoginFragment::class.java.simpleName, false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun connectViews() {
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.keep_stay, R.anim.exit_to_down)
    }
}