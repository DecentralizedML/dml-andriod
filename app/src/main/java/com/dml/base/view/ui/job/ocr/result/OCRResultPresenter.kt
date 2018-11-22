package com.dml.base.view.ui.job.ocr.result

class OCRResultPresenter(var view: OCRResultContract.View) : OCRResultContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}