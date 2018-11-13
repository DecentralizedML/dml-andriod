package com.dml.base.view.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.dml.base.R
import com.dml.base.base.BaseActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_qrcode_scanner.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QRCodeScannerActivity : BaseActivity(), ZXingScannerView.ResultHandler {

    companion object {
        private const val REQUEST_CAMERA = 1001
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScannerProperties()
    }

    private fun setScannerProperties() {
        qrCodeScanner.setFormats(listOf(BarcodeFormat.QR_CODE))
        qrCodeScanner.setAutoFocus(true)
        qrCodeScanner.setLaserColor(R.color.colorAccent)
        qrCodeScanner.setMaskColor(R.color.colorAccent)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CAMERA)
                return
            }
        }
        qrCodeScanner.startCamera()
        qrCodeScanner.setResultHandler(this)
    }

    public override fun onPause() {
        super.onPause()
        qrCodeScanner.stopCamera()
    }


    override fun setLayoutId(): Int {
        return R.layout.activity_qrcode_scanner
    }

    override fun connectViews() {
    }

    override fun handleResult(rawResult: Result) {
        val intent = Intent()
        intent.putExtra("result", rawResult.text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}