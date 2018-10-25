package com.dml.base.activity

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat
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
        if (emailET?.text.isNullOrEmpty()
                || passwordET?.text.isNullOrEmpty())
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
                        var intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        ActivityCompat.finishAffinity(context as Activity)
                        finish()
                    }

                    override fun onComplete() {
                        super.onComplete()
                        loginBtn?.showProgressBar(false)
                        loginBtn?.isEnabled = true
                    }

                    override fun onStart() {
                        super.onStart()
                        loginBtn?.showProgressBar(true)
                        loginBtn?.isEnabled = false
                    }
                })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.keep_stay, R.anim.exit_to_down)
    }
}