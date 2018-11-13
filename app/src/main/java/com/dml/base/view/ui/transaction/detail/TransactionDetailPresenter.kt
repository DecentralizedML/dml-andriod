package com.dml.base.view.ui.transaction.detail

class TransactionDetailPresenter(var view: TransactionDetailContract.View) : TransactionDetailContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}