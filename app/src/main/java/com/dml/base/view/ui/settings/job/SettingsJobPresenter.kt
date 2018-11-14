package com.dml.base.view.ui.settings.job

class SettingsJobPresenter(var view: SettingsJobContract.View) : SettingsJobContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}