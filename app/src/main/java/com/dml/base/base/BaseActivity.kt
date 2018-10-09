package com.dml.base.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        connectViews()
    }

    protected abstract fun setLayoutId(): Int

    protected abstract fun connectViews()
}