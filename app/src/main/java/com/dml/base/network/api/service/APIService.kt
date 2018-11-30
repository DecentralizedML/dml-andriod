package com.dml.base.network.api.service

import com.dml.base.network.api.Endpoint
import com.dml.base.network.model.*
import io.reactivex.Observable

class APIService : BaseService<Endpoint>() {

    override fun getEndpointClass(): Class<Endpoint> = Endpoint::class.java

    fun postUserSignUpRequest(userSignUpRequest: UserSignUpRequest): Observable<UserSignUpResponse>? {
        return getServiceInstance()?.postUserSignUp(userSignUpRequest)
    }

    fun updateUserRequest(userUpdateRequest: UserUpdateRequest): Observable<UserUpdateResponse>? {
        return getServiceInstance()?.updateUser(userUpdateRequest)
    }

    fun postUserLoginRequest(userLoginRequest: UserLoginRequest): Observable<UserLoginResponse>? {
        return getServiceInstance()?.postUserLogin(userLoginRequest)
    }
}