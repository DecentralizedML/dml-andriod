package com.dml.base.view.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.model.UserLoginRequest
import com.dml.base.network.model.UserLoginResponse
import com.dml.base.view.activity.MainActivity
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = LoginPresenter(this)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun connectViews() {
        loginButton?.apply {
            setText(R.string.login)
            setOnClickListener { presenter.onLoginButtonClicked(emailEditText?.text.toString(), passwordEditText?.text.toString()) }
        }
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

        mParentActivity?.mService?.postUserLoginRequest(loginRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserLoginResponse>(context) {
                    override fun onNext(modelUser: UserLoginResponse) {
                        Preferences.setJWT(context, modelUser.jwt)
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        mParentActivity?.startActivity(intent)
                        mParentActivity?.finishAffinity()
                        mParentActivity?.finish()
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
}