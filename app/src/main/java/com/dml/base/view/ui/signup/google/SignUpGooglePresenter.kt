package com.dml.base.view.ui.signup.google

import com.dml.base.Utility


class SignUpGooglePresenter(var view: SignUpGoogleContract.View) : SignUpGoogleContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onSignUpButtonClicked(email: String?, password: String?, isChecked: Boolean) {
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

        if (!isChecked)
            valid = false
        //Add not check reminder?

        if (valid)
            view.postSignUpRequest()
    }

}