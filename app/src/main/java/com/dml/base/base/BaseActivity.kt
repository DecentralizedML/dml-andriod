package com.dml.base.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dml.base.api.service.APIService

abstract class BaseActivity : AppCompatActivity() {

    val mService = APIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        connectViews()
    }

    protected abstract fun setLayoutId(): Int

    protected abstract fun connectViews()
}