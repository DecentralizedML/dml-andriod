package com.dml.base.view.ui.login

import com.dml.base.base.BaseContract

class LoginContract {
    interface View : BaseContract.View<Presenter> {
        fun postLoginRequest()
        fun showEmailError()
        fun showEmailNoError()
        fun showPasswordError()
        fun showPasswordNoError()
    }

    interface Presenter : BaseContract.Presenter {
        fun onLoginButtonClicked(email: String?, password: String?)
    }
}