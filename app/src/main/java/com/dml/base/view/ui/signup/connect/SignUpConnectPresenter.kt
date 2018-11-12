package com.dml.base.view.ui.signup.connect

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build

class SignUpConnectPresenter(var view: SignUpConnectContract.View) : SignUpConnectContract.Presenter {

    private val permissionArray = ArrayList<String>()

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun onSmsButtonClicked() {
        if (permissionArray.contains(Manifest.permission.READ_SMS)) {
            permissionArray.remove(Manifest.permission.READ_SMS)
            view.setSmsButtonFilled(false)
        } else {
            permissionArray.add(Manifest.permission.READ_SMS)
            view.setAddressBookButtonFilled(true)
        }
    }

    override fun onCameraButtonClicked() {
        if (permissionArray.contains(Manifest.permission.CAMERA)) {
            permissionArray.remove(Manifest.permission.CAMERA)
            view.setCameraButtonFilled(false)
        } else {
            permissionArray.add(Manifest.permission.CAMERA)
            view.setCameraButtonFilled(true)
        }
    }

    override fun onAddressBookButtonClicked() {
        if (permissionArray.contains(Manifest.permission.READ_CONTACTS)) {
            permissionArray.remove(Manifest.permission.READ_CONTACTS)
            view.setAddressBookButtonFilled(false)
        } else {
            permissionArray.add(Manifest.permission.READ_CONTACTS)
            view.setAddressBookButtonFilled(true)
        }
    }

    override fun onNextButtonClicked() {
        if (permissionArray.size == 0 || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            view.redirectToSignUpComplete()
        } else {
            view.requestPermission(permissionArray.toTypedArray(), SignUpConnectFragment.REQUEST_CODE_PERMISSION)
        }
    }

    override fun onPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            SignUpConnectFragment.REQUEST_CODE_PERMISSION -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    view.showPermissionDeniedDialog()
                } else {
                    view.redirectToSignUpComplete()
                }
                return
            }
        }
    }
}