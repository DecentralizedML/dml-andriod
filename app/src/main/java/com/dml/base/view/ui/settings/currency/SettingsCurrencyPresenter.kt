package com.dml.base.view.ui.settings.currency

class SettingsCurrencyPresenter(var view: SettingsCurrencyContract.View) : SettingsCurrencyContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}