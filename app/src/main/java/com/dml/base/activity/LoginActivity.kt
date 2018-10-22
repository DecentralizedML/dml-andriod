package com.dml.base.activity

import android.widget.Toast
import com.dml.base.Preferences
import com.dml.base.R
import com.dml.base.base.BaseActivity
import com.dml.base.connection.DefaultRequestObserver
import com.dml.base.model.UserLoginModel
import com.dml.base.model.UserLoginRequestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun connectViews() {
        loginBtn?.apply {
            setText(R.string.login)
            setOnClickListener { postLoginRequest() }
        }
    }

    private fun postLoginRequest() {
        if (emailET?.text.isNullOrEmpty() || passwordET?.text.isNullOrEmpty())
            return

        var loginRequestModel = UserLoginRequestModel()
        loginRequestModel.apply {
            email = emailET?.text.toString()
            password = passwordET?.text.toString()
        }

        mService.postUserLoginRequest(this@LoginActivity, loginRequestModel)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DefaultRequestObserver<UserLoginModel>(this@LoginActivity) {
                    override fun onNext(modelUser: UserLoginModel) {
                        Preferences.setJWT(context, modelUser.jwt)
                        Toast.makeText(context, "postUserLoginRequest success", Toast.LENGTH_SHORT).show()
                    }

                    override fun onComplete() {
                        super.onComplete()
                        loginBtn?.showProgressBar(false)
                    }

                    override fun onStart() {
                        super.onStart()
                        loginBtn?.showProgressBar(true)
                    }
                })
    }

}