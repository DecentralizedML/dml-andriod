package com.dml.base.view.ui.signup.securityquestion

import com.dml.base.view.ui.transaction.TransactionContract

class SignUpSecurityQuestionPresenter(var view: SignUpSecurityQuestionContract.View) : SignUpSecurityQuestionContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}