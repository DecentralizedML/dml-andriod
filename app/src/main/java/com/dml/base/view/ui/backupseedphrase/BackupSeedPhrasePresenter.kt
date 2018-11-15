package com.dml.base.view.ui.backupseedphrase

class BackupSeedPhrasePresenter(var view: BackupSeedPhraseContract.View) : BackupSeedPhraseContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}