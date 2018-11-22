package com.dml.base.view.ui.job.ocr.camera

class OCRCameraPresenter(var view: OCRCameraContract.View) : OCRCameraContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }
}