package com.dml.base.view.ui.backupseedphrase.confirm

class BackupSeedPhraseConfirmPresenter(var view: BackupSeedPhraseConfirmContract.View) : BackupSeedPhraseConfirmContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}