package com.dml.base.view.ui.signup.signup

class SignUpPresenter(var view: SignUpContract.View) : SignUpContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}