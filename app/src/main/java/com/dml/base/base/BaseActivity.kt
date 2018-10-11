package com.dml.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dml.base.api.service.APIService
import android.content.Intent



abstract class BaseActivity : AppCompatActivity() {

    val mService = APIService()

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
}