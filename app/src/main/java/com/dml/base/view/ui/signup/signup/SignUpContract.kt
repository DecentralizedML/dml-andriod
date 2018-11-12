package com.dml.base.view.ui.signup.signup

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class SignUpContract {

    interface View : BaseView<Presenter> {
        fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>)
    }

    interface Presenter : BasePresenter {

    }
}