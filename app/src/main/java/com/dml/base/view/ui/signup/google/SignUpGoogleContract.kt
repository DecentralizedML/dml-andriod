package com.dml.base.view.ui.signup.google

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class SignUpGoogleContract {

    interface View : BaseView<Presenter> {
        fun postSignUpRequest()
        fun showEmailError()
        fun showEmailNoError()
        fun showPasswordError()
        fun showPasswordNoError()
        fun redirectToSignUpInformation()
    }

    interface Presenter : BasePresenter {
        fun onSignUpButtonClicked(email: String?, password: String?, isChecked: Boolean)
    }
}