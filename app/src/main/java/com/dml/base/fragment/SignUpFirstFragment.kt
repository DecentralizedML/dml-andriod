package com.dml.base.fragment

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.api.service.APIService
import com.dml.base.base.BaseFragment
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.model.LoginModel
import com.dml.base.model.LoginRequestModel
import com.dml.base.model.SignUpModel
import com.dml.base.model.SignUpRequestModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_signup_first.*

class SignUpFirstFragment : BaseFragment() {

    var showPassword = false
    var callbackManager: CallbackManager? = null

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = SignUpFirstFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_signup_first
    }

    override fun connectViews() {
//        passwordET?.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View, event: MotionEvent): Boolean {
//                val DRAWABLE_LEFT = 0
//                val DRAWABLE_TOP = 1
//                val DRAWABLE_RIGHT = 2
//                val DRAWABLE_BOTTOM = 3
//
//                if (event.action == MotionEvent.ACTION_UP) {
//                    if (event.rawX >= passwordET.getRight() - passwordET.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
//                        if (showPassword) {
//                            showPassword = false
//                            passwordET?.transformationMethod = null
//                        } else {
//                            showPassword = true
//                            passwordET?.transformationMethod = PasswordTransformationMethod.getInstance()
//                        }
//
//                        val start = passwordET.selectionStart
//                        var end = passwordET.selectionEnd
//                        passwordET?.setSelection(start, end)
//
//                        return true
//                    }
//                }
//                return false
//            }
//        })

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

        signUpBtn?.setOnClickListener { signUp() }
    }

    private fun postSignUpRequest() {
        var signUpRequestModel = SignUpRequestModel()
        signUpRequestModel.apply {
            user.apply {
                email = "test2@test.com"
                password = "password123"
                passwordConfirmation = "password123"
                firstName = "Ethan"
                lastName = "Chan"
            }
        }

        getParentActivity().mService.postSignUpRequest(context, signUpRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<SignUpModel>(context) {
                    override fun onNext(model: SignUpModel) {
                        Preferences.setJWT(context, model.jwt)
                        Toast.makeText(context, "postSignUpRequest success", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun postLoginRequest() {
        var loginRequestModel = LoginRequestModel()
        loginRequestModel.apply {
            email = "user@kyokan.io"
            password = "password123"
        }

        getParentActivity().mService.postLoginRequest(context, loginRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<LoginModel>(context) {
                    override fun onNext(model: LoginModel) {
                        Preferences.setJWT(context, model.jwt)
                        Toast.makeText(context, "postLoginRequest success", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun signUp() {
        postSignUpRequest()
//        postLoginRequest()
//        if (Utility.isValidEmail(emailET?.text.toString()))
//            Toast.makeText(activity, "valid", Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(activity, "invalid", Toast.LENGTH_SHORT).show()

//        if (activity is SignUpActivity) {
//            (activity as SignUpActivity).setState(SignUpState.Second)
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        //super.onActivityResult(requestCode, resultCode, data);
    }
}