package com.dml.base.network.api.service

import com.dml.base.network.api.Endpoint
import com.dml.base.network.model.UserLoginRequest
import com.dml.base.network.model.UserLoginResponse
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.network.model.UserSignUpResponse
import io.reactivex.Observable

class APIService : BaseService<Endpoint>() {

    override fun getEndpointClass(): Class<Endpoint> = Endpoint::class.java

    fun postUserSignUpRequest(userSignUpRequest: UserSignUpRequest): Observable<UserSignUpResponse>? {
        return getServiceInstance()?.postUserSignUp(userSignUpRequest)
    }

    fun updateUserRequest(userSignUpRequest: UserSignUpRequest): Observable<UserSignUpResponse>? {
        return getServiceInstance()?.updateUser(userSignUpRequest)
    }

    fun postUserLoginRequest(userLoginRequest: UserLoginRequest): Observable<UserLoginResponse>? {
        return getServiceInstance()?.postUserLogin(userLoginRequest)
    }
}