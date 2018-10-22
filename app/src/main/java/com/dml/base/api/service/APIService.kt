package com.dml.base.api.service

import android.content.Context
import com.dml.base.api.Endpoint
import com.dml.base.model.UserLoginModel
import com.dml.base.model.UserLoginRequestModel
import com.dml.base.model.UserSignUpModel
import com.dml.base.model.UserSignUpRequestModel
import io.reactivex.Observable

class APIService : BaseService<Endpoint>() {

    override fun getEndpointClass(): Class<Endpoint> = Endpoint::class.java

    fun postUserSignUpRequest(context: Context, userSignUpRequestModel: UserSignUpRequestModel): Observable<UserSignUpModel>? {
        return getServiceInstance(context)?.postUserSignUp(userSignUpRequestModel)
    }
    fun putUserRequest(context: Context, userSignUpRequestModel: UserSignUpRequestModel): Observable<UserSignUpModel>? {
        return getServiceInstance(context)?.postUserSignUp(userSignUpRequestModel)
    }

    fun postUserLoginRequest(context: Context, userLoginRequestModel: UserLoginRequestModel): Observable<UserLoginModel>? {
        return getServiceInstance(context)?.postUserLogin(userLoginRequestModel)
    }
}