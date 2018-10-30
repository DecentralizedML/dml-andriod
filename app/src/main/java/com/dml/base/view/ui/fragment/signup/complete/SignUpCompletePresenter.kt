package com.dml.base.view.ui.fragment.signup.complete

class SignUpCompletePresenter(var view: SignUpCompleteContract.View) : SignUpCompleteContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}