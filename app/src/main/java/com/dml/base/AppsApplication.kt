package com.dml.base

import android.app.Application
import android.util.Log
import com.facebook.FacebookSdk

class AppsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
    }
}