package com.dml.base.view.ui.settings.profile

class SettingsProfilePresenter(var view: SettingsProfileContract.View) : SettingsProfileContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}