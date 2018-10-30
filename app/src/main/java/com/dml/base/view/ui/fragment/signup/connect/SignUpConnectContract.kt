package com.dml.base.view.ui.fragment.signup.connect

import com.dml.base.base.BasePresenter
import com.dml.base.base.BaseView

class SignUpConnectContract {

    interface View : BaseView<Presenter> {
        fun setSmsButtonFilled(filled: Boolean)
        fun setCameraButtonFilled(filled: Boolean)
        fun setAddressBookButtonFilled(filled: Boolean)
        fun redirectToSignUpComplete()
        fun requestPermission(permissions: Array<String>, requestCode: Int)
        fun showPermissionDeniedDialog()
    }

    interface Presenter : BasePresenter {
        fun onSmsButtonClicked()
        fun onCameraButtonClicked()
        fun onAddressBookButtonClicked()
        fun onNextButtonClicked()
        fun onPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    }
}