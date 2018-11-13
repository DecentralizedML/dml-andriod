package com.dml.base.view.ui.cashout


class CashOutPresenter(var view: CashOutContract.View) : CashOutContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}