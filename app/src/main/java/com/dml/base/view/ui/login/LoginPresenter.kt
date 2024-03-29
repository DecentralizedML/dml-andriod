package com.dml.base.view.ui.login

import com.dml.base.Utility

class LoginPresenter(var view: LoginContract.View) : LoginContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onLoginButtonClicked(email: String?, password: String?) {
        var valid = true

        if (email.isNullOrEmpty() or email.isNullOrBlank()) {
            view.showEmailError()
            valid = false
        } else {
            email?.let {
                if (!Utility.isValidEmail(email)) {
                    view.showEmailError()
                    valid = false
                } else {
                    view.showEmailNoError()
                }
            }
        }

        if (password.isNullOrEmpty() or password.isNullOrBlank()) {
            view.showPasswordError()
            valid = false
        } else {
            password?.let {
                if (password.length < 8) {
                    view.showPasswordError()
                    valid = false
                } else {
                    view.showPasswordNoError()
                }
            }
        }

        if (valid)
            view.postLoginRequest()
    }
}