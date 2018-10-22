package com.dml.base.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dml.base.api.service.APIService
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity : AppCompatActivity() {

    val mService = APIService()

    var mCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        connectViews()
    }

    protected abstract fun setLayoutId(): Int

    protected abstract fun connectViews()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (!mCompositeDisposable.isDisposed)
            mCompositeDisposable.dispose()
    }
}