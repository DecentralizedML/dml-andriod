package com.dml.base.view.ui.signup.google

import android.os.Bundle
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.view.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.fragment_signup_google.*

class SignUpGoogleFragment : BaseFragment(), SignUpGoogleContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpGoogleFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SignUpGoogleContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignUpGooglePresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_google
    }

    override fun connectViews() {
        emailEditText?.setText((mParentActivity as SignUpActivity).getUserSignUpRequestModel()?.user?.email)

        signUpButton?.apply {
            setText(R.string.fragment_signup_button_sign_up)
            setOnClickListener {
                presenter.onSignUpButtonClicked(emailEditText.text.toString()
                        , passwordEditText.text.toString()
                        , agreeCheckBox.isChecked)
            }
        }
    }

    override fun setPresenter(presenter: SignUpGoogleContract.Presenter) {
        this.presenter = presenter
    }

    override fun postSignUpRequest() {
        //request and handle with google signup
        redirectToSignUpInformation()
    }

    override fun showEmailError() {
        emailTextInputLayout?.isErrorEnabled = true
        emailTextInputLayout?.error = getString(R.string.error_email_format)
    }

    override fun showEmailNoError() {
        emailTextInputLayout?.isErrorEnabled = false
    }

    override fun showPasswordError() {
        passwordTextInputLayout?.isErrorEnabled = true
        passwordTextInputLayout?.error = getString(R.string.error_password_format)
    }

    override fun showPasswordNoError() {
        passwordTextInputLayout?.isErrorEnabled = false
    }

    override fun redirectToSignUpInformation() {
        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Information)
    }

    override fun showProgressBar() {
    }

    override fun dismissProgressBar() {
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
    }
}