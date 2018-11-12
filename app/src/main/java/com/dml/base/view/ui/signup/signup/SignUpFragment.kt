package com.dml.base.view.ui.signup.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseFragment
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.network.model.UserSignUpResponse
import com.dml.base.view.ui.signup.SignUpActivity
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

    private lateinit var presenter : SignUpContract.Presenter

    var facebookCallbackManager: CallbackManager? = null
    var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)

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
        return R.layout.fragment_signup
    }

    override fun connectViews() {
        fbSignUpButton?.setOnClickListener { signUpByFacebook() }
        googleSignUpButton?.setOnClickListener { signUpByGoogle() }
        signUpButton?.apply {
            setText(R.string.activity_signup_button_sign_up)
            setOnClickListener { signUpByEmail() }
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
        activity?.startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_UP)
    }

    private fun signUpByEmail() {
        if (!Utility.isValidEmail(emailEditText?.text.toString())) {
            Toast.makeText(activity, "email invalid", Toast.LENGTH_SHORT).show()
            return
        }

        if (passwordEditText?.text.isNullOrBlank()) {
            Toast.makeText(activity, "password invalid", Toast.LENGTH_SHORT).show()
            return
        }

        if (!agreeCheckBox.isChecked)
            return

        postSignUpRequest()
    }

    private fun postSignUpRequest() {
        val signUpRequestModel = UserSignUpRequest()
        signUpRequestModel.apply {
            user.apply {
                email = emailEditText?.text.toString()
                password = passwordEditText?.text.toString()
                passwordConfirmation = passwordEditText?.text.toString()
                firstName = ""
                lastName = ""
            }
        }

        mParentActivity?.mService?.postUserSignUpRequest(signUpRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserSignUpResponse>(context) {
                    override fun onNext(modelUser: UserSignUpResponse) {
                        Preferences.setJWT(context, modelUser.jwt)
                        (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Information)
                    }

                    override fun onComplete() {
                        super.onComplete()
                        signUpButton?.showProgressBar(false)
                        signUpButton?.isEnabled = true
                    }

                    override fun onStart() {
                        super.onStart()
                        signUpButton?.showProgressBar(true)
                        signUpButton?.isEnabled = false
                    }
                })?.let { mCompositeDisposable.add(it) }
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
            (mParentActivity as SignUpActivity).setState(SignUpActivity.SignUpState.Google)

        } catch (e: ApiException) {
            Utility.Error(TAG, "fail: ${e.statusCode}")
        }
    }
}