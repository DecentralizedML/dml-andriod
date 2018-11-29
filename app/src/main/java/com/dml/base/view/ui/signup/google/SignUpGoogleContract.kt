package com.dml.base.view.ui.signup.google

import com.dml.base.base.BaseContract

class SignUpGoogleContract {

    interface View : BaseContract.View<Presenter> {
        fun postSignUpRequest()
        fun showEmailError()
        fun showEmailNoError()
        fun showPasswordError()
        fun showPasswordNoError()
        fun redirectToSignUpInformation()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSignUpButtonClicked(email: String?, password: String?, isChecked: Boolean)
    }
}