package com.dml.base.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.activity.SignUpActivity
import com.dml.base.base.BaseFragment
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.model.UserSignUpModel
import com.dml.base.model.UserSignUpRequestModel
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

class SignUpFragment : BaseFragment() {

    val REQUEST_CODE_GOOGLE_SIGN_UP = 1001

    var callbackManager: CallbackManager? = null
    var mGoogleSignInClient: GoogleSignInClient? = null

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup
    }

    override fun connectViews() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
                .registerCallback(callbackManager,
                        object : FacebookCallback<LoginResult> {
                            override fun onSuccess(loginResult: LoginResult) {
                                Log.d("facebook", loginResult.toString())
                                // App code
                            }

                            override fun onCancel() {
                                Log.d("facebook", "cancel")
                                // App code
                            }

                            override fun onError(exception: FacebookException) {
                                Log.d("facebook", exception.message)
                                // App code
                            }
                        })

        fbSignUpBtn?.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(getParentActivity(), arrayListOf("public_profile", "email"))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
        val account = GoogleSignIn.getLastSignedInAccount(context)

        googleSignUpBtn?.setOnClickListener {
            signUpByGoogle()
        }

        signUpBtn?.apply {
            setText(R.string.activity_signup_button_sign_up)
            setOnClickListener { signUpByEmail() }
        }
    }

    private fun signUpByGoogle() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        activity?.startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_UP)
    }

    private fun signUpByEmail() {
        if (!Utility.isValidEmail(emailET?.text.toString())) {
            Toast.makeText(activity, "email invalid", Toast.LENGTH_SHORT).show()
            return
        }

        if (passwordET?.text.isNullOrBlank()) {
            Toast.makeText(activity, "password invalid", Toast.LENGTH_SHORT).show()
            return
        }

        if (!agreeRadioBtn.isChecked)
            return

        postSignUpRequest()
//        //Testing
//        (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Information)
    }

    private fun postSignUpRequest() {
        var signUpRequestModel = UserSignUpRequestModel()
        signUpRequestModel.apply {
            user.apply {
                email = emailET?.text.toString()
                password = passwordET?.text.toString()
                passwordConfirmation = passwordET?.text.toString()
                firstName = ""
                lastName = ""
            }
        }

        getParentActivity().mService.postUserSignUpRequest(context, signUpRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserSignUpModel>(context) {
                    override fun onNext(modelUser: UserSignUpModel) {
                        Preferences.setJWT(context, modelUser.jwt)
                        (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Information)
                    }

                    override fun onComplete() {
                        super.onComplete()
                        signUpBtn?.showProgressBar(false)
                        signUpBtn?.isEnabled = true
                    }

                    override fun onStart() {
                        super.onStart()
                        signUpBtn?.showProgressBar(true)
                        signUpBtn?.isEnabled = false
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_UP) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val userSignUpRequestModel = UserSignUpRequestModel()
            userSignUpRequestModel.apply {
                user.email = if (account?.email == null) "" else account.email.toString()
            }

            (activity as SignUpActivity).updateUserSignUpRequestModel(userSignUpRequestModel)
            (activity as SignUpActivity).setState(SignUpActivity.SignUpState.Google)

        } catch (e: ApiException) {
            Log.d("MainActivity", "fail: ${e.statusCode}")
        }

    }
}