package com.dml.base.network.api.service

import com.dml.base.connection.RetrofitClient

abstract class BaseService<EndpointClass> {
    private var mService: EndpointClass? = null

    protected abstract fun getEndpointClass(): Class<EndpointClass>

    fun BaseService() {
        mService = RetrofitClient.getDefaultInstance().create(getEndpointClass())
    }

    protected fun getServiceInstance(): EndpointClass? {
        if (mService == null) {
            mService = RetrofitClient.getDefaultInstance().create(getEndpointClass())
        }
        return mService
    }
}