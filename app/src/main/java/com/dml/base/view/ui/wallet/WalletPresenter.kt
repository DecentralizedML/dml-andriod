package com.dml.base.view.ui.wallet

import com.dml.base.view.ui.wallet.detail.WalletDetailContract

class WalletPresenter(var view: WalletContract.View) : WalletContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}