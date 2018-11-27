package com.dml.base.view.ui.transaction

class TransactionPresenter(var view: TransactionContract.View) : TransactionContract.Presenter {

    companion object {
        const val TYPE_DATE = 0
        const val TYPE_PENDING = 1
        const val TYPE_TOKEN = 2
        const val TYPE_FIAT_VALUE = 3
    }

    private var currentType = TYPE_DATE

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onDateButtonClicked() {
        if (currentType == TYPE_DATE) return
        currentType = TYPE_DATE
        view.turnOnDateButton()
        view.turnOffPendingButton()
        view.turnOffTokenButton()
        view.turnOffFiatValueButton()
    }

    override fun onPendingButtonClicked() {
        if (currentType == TYPE_PENDING) return
        currentType = TYPE_PENDING
        view.turnOffDateButton()
        view.turnOnPendingButton()
        view.turnOffTokenButton()
        view.turnOffFiatValueButton()
    }

    override fun onTokenButtonClicked() {
        if (currentType == TYPE_TOKEN) return
        currentType = TYPE_TOKEN
        view.turnOffDateButton()
        view.turnOffPendingButton()
        view.turnOnTokenButton()
        view.turnOffFiatValueButton()
    }

    override fun onFiatValueButtonClicked() {
        if (currentType == TYPE_FIAT_VALUE) return
        currentType = TYPE_FIAT_VALUE
        view.turnOffDateButton()
        view.turnOffPendingButton()
        view.turnOffTokenButton()
        view.turnOnFiatValueButton()
    }
}