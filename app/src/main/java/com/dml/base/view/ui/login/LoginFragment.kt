package com.dml.base.view.ui.login

import android.content.Intent
import android.os.Bundle
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.model.UserLoginRequest
import com.dml.base.network.model.UserLoginResponse
import com.dml.base.view.ui.main.MainActivity
import com.dml.base.view.ui.signup.signup.SignUpFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), LoginContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = LoginFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: LoginContract.Presenter

    var facebookCallbackManager: CallbackManager? = null
    var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = LoginPresenter(this)

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


        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
//        val account = GoogleSignIn.getLastSignedInAccount(context)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun connectViews() {
        fbSignUpButton?.setOnClickListener { signUpByFacebook() }
        googleSignUpButton?.setOnClickListener { signUpByGoogle() }
        loginButton?.apply {
            setText(R.string.login)
            setOnClickListener { presenter.onLoginButtonClicked(emailEditText?.text.toString(), passwordEditText?.text.toString()) }
        }
    }

    private fun signUpByFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(mParentActivity, arrayListOf("public_profile", "email"))
    }

    private fun signUpByGoogle() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        activity?.startActivityForResult(signInIntent, SignUpFragment.REQUEST_CODE_GOOGLE_SIGN_UP)
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun postLoginRequest() {
        val loginRequestModel = UserLoginRequest()
        loginRequestModel.apply {
            email = emailEditText?.text.toString()
            password = passwordEditText?.text.toString()
        }

        mParentActivity.mService?.postUserLoginRequest(loginRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserLoginResponse>(context) {
                    override fun onNext(modelUser: UserLoginResponse) {
                        Preferences.setJWT(context, modelUser.meta.jwt)
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        mParentActivity.startActivity(intent)
                        mParentActivity.finishAffinity()
                        mParentActivity.finish()
                    }

                    override fun onComplete() {
                        super.onComplete()
                        loginButton?.showProgressBar(false)
                        loginButton?.isEnabled = true
                    }

                    override fun onStart() {
                        super.onStart()
                        loginButton?.showProgressBar(true)
                        loginButton?.isEnabled = false
                    }
                })?.let { mCompositeDisposable.add(it) }
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

        if (requestCode == SignUpFragment.REQUEST_CODE_GOOGLE_SIGN_UP) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            emailEditText.setText(account?.email.toString())

        } catch (e: ApiException) {
            Utility.Error(SignUpFragment.TAG, "fail: ${e.statusCode}")
        }
    }
}