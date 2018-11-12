package com.dml.base.view.ui.login

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class LoginContract {
    interface View : BaseView<Presenter> {
        fun postLoginRequest()
        fun showEmailError()
        fun showEmailNoError()
        fun showPasswordError()
        fun showPasswordNoError()
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked(email: String?, password: String?)
    }
}