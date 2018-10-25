package com.dml.base.activity

import android.content.Intent
import android.os.Bundle
import com.dml.base.R
import com.dml.base.Utility
import com.dml.base.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mCompositeDisposable.add(Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (Utility.isLoggedIn(this)) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                        finish()
                    }
                })
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun connectViews() {

    }
}