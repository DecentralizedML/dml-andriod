package com.dml.base.view.ui.signup.signup

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class SignUpContract {

    interface View : BaseView<Presenter> {
        fun postSignUpRequest()
        fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>)
        fun showEmailError()
        fun showEmailNoError()
        fun showPasswordError()
        fun showPasswordNoError()
    }

    interface Presenter : BasePresenter {
        fun onSignUpButtonClicked(email: String?, password: String?, isChecked: Boolean)
    }
}