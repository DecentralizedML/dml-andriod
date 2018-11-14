package com.dml.base.view.ui.settings.datasource

class SettingsDataSourcePresenter(var view: SettingsDataSourceContract.View) : SettingsDataSourceContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}