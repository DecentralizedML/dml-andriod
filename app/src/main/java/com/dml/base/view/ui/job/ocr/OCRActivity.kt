package com.dml.base.view.ui.job.ocr


import com.dml.base.R
import com.dml.base.base.BaseFragmentActivity
import com.dml.base.view.ui.job.ocr.camera.OCRCameraFragment

class OCRActivity : BaseFragmentActivity() {

    override fun createBaseFragment() {
        startFragment(OCRCameraFragment.newInstance(null), OCRCameraFragment::class.java.simpleName, false)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_ocr
    }

    override fun connectViews() {
    }
}