package com.dml.base.view.ui.wallet.detail

class WalletDetailPresenter(var view: WalletDetailContract.View) : WalletDetailContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}