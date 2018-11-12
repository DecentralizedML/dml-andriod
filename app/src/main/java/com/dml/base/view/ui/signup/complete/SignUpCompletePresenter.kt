package com.dml.base.view.ui.signup.complete

class SignUpCompletePresenter(var view: SignUpCompleteContract.View) : SignUpCompleteContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}