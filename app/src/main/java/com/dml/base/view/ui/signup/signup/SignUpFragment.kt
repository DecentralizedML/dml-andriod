package com.dml.base.view.ui.signup.signup

import android.content.Intent
import android.os.Bundle
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.network.ErrorResponse
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.view.ui.signup.SignUpActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : BaseFragment(), SignUpContract.View {

    companion object {

        val TAG = SignUpFragment::class.java.simpleName
        const val REQUEST_CODE_GOOGLE_SIGN_UP = 1001

        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: SignUpContract.Presenter

    private var facebookCallbackManager: CallbackManager? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this, mParentActivity.mService, mCompositeDisposable)

        facebookCallbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
                .registerCallback(facebookCallbackManager,
                        object : FacebookCallback<LoginResult> {
                            override fun onSuccess(loginResult: LoginResult) {
                            }

                            override fun onCancel() {
                            }

                            override fun onError(exception: FacebookException) {
                            }
                        })

        mGoogleSignInClient = Utility.getGoogleSignInClient(context)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup
    }

    override fun connectViews() {
        fbSignUpButton?.setOnClickListener { signUpByFacebook() }
        googleSignUpButton?.setOnClickListener { signUpByGoogle() }
        signUpButton?.apply {
            setText(R.string.fragment_signup_button_sign_up)
            setOnClickListener {
                presenter.onSignUpButtonClicked(emailEditText.text.toString()
                        , passwordEditText.text.toString()
                        , agreeCheckBox.isChecked)
            }
        }
    }

    override fun setPresenter(presenter: SignUpContract.Presenter) {
        this.presenter = presenter
    }

    private fun signUpByFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(mParentActivity, arrayListOf("public_profile", "email"))
    }

    private fun signUpByGoogle() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        mParentActivity.startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_UP)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager?.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_UP) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    override fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val userSignUpRequestModel = UserSignUpRequest()
            userSignUpRequestModel.apply {
                user.email = if (account?.email == null) "" else account.email.toString()
            }

            (mParentActivity as SignUpActivity).updateUserSignUpRequestModel(userSignUpRequestModel)
            redirectToGooglePage()

        } catch (e: ApiException) {
            Utility.Error(TAG, "fail: ${e.statusCode}")
        }
    }

    override fun saveJWT(jwt: String) {
        Preferences.setJWT(context, jwt)
    }

    override fun redirectToInformationPage() {
        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Information)
    }

    override fun redirectToGooglePage() {
        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Google)
    }

    override fun showProgressBar() {
        signUpButton?.showProgressBar(true)
        signUpButton?.isEnabled = false
    }

    override fun dismissProgressBar() {
        signUpButton?.showProgressBar(false)
        signUpButton?.isEnabled = true
    }

    override fun showErrorResponse(errorResponse: ErrorResponse) {
        Utility.showDialog(context, errorResponse.toString())
    }

}