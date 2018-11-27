package com.dml.base.view.ui.signup.connect

import android.os.Bundle
import android.widget.Toast
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.view.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.fragment_signup_connect.*

class SignUpConnectFragment : BaseFragment(), SignUpConnectContract.View {

    companion object {
        const val REQUEST_CODE_PERMISSION = 1001

        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpConnectFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SignUpConnectContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignUpConnectPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_connect
    }

    override fun connectViews() {
        smsButton?.setOnClickListener { presenter.onSmsButtonClicked() }
        cameraButton?.setOnClickListener { presenter.onCameraButtonClicked() }
        addressBookButton?.setOnClickListener { presenter.onAddressBookButtonClicked() }
        nextButton?.apply {
            setText(R.string.fragment_signup_information_button_next)
            showRightIcon(true)
            setOnClickListener { presenter.onNextButtonClicked() }
        }
    }

    override fun setPresenter(presenter: SignUpConnectContract.Presenter) {
        this.presenter = presenter
    }

    override fun setSmsButtonFilled(filled: Boolean) {
        if (filled)
            smsButton?.setImageResource(R.drawable.bg_circle_filled)
        else
            smsButton?.setImageResource(R.drawable.bg_circle)
    }

    override fun setCameraButtonFilled(filled: Boolean) {
        if (filled)
            cameraButton?.setImageResource(R.drawable.bg_circle_filled)
        else
            cameraButton?.setImageResource(R.drawable.bg_circle)
    }

    override fun setAddressBookButtonFilled(filled: Boolean) {
        if (filled)
            addressBookButton?.setImageResource(R.drawable.bg_circle_filled)
        else
            addressBookButton?.setImageResource(R.drawable.bg_circle)
    }

    override fun requestPermission(permissions: Array<String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    override fun redirectToSignUpComplete() {
        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Complete)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        presenter.onPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun showPermissionDeniedDialog() {
        Toast.makeText(context, "{Permission denied}", Toast.LENGTH_SHORT).show()
    }
}