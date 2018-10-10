package com.dml.base.api.service

import android.content.Context
import com.dml.base.connection.RetrofitClient

abstract class BaseService<EndpointClass> {
    private var mService: EndpointClass? = null

    protected abstract fun getEndpointClass(): Class<EndpointClass>

    fun BaseService(context: Context) {
        mService = RetrofitClient.getDefaultInstance(context).create(getEndpointClass())
    }

    protected fun getServiceInstance(context: Context): EndpointClass? {
        if (mService == null) {
            mService = RetrofitClient.getDefaultInstance(context).create(getEndpointClass())
        }
        return mService
    }
}