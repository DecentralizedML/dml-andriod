package com.dml.base.view.ui.signup.connect

import com.dml.base.base.BaseContract

class SignUpConnectContract {

    interface View : BaseContract.View<Presenter> {
        fun setSmsButtonFilled(filled: Boolean)
        fun setCameraButtonFilled(filled: Boolean)
        fun setAddressBookButtonFilled(filled: Boolean)
        fun redirectToSignUpComplete()
        fun requestPermission(permissions: Array<String>, requestCode: Int)
        fun showPermissionDeniedDialog()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSmsButtonClicked()
        fun onCameraButtonClicked()
        fun onAddressBookButtonClicked()
        fun onNextButtonClicked()
        fun onPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    }
}