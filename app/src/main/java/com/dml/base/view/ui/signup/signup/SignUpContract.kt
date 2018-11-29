package com.dml.base.view.ui.signup.signup

import com.dml.base.base.BaseContract
import com.dml.base.network.model.UserSignUpRequest
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class SignUpContract {

    interface View : BaseContract.View<Presenter> {
        fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>)
        fun showEmailError()
        fun showEmailNoError()
        fun showPasswordError()
        fun showPasswordNoError()

        fun saveJWT(jwt: String)
        fun redirectToInformationPage()
        fun redirectToGooglePage()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSignUpButtonClicked(email: String, password: String, isChecked: Boolean)
        fun postSignUpRequest(signUpRequestModel: UserSignUpRequest)
    }
}