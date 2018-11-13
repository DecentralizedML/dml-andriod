package com.dml.base.view.ui.transaction

class TransactionPresenter(var view: TransactionContract.View) : TransactionContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}