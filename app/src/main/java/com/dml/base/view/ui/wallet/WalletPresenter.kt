package com.dml.base.view.ui.wallet

class WalletPresenter(var view: WalletContract.View) : WalletContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}