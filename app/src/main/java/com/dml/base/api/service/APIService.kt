package com.dml.base.api.service

import android.content.Context
import com.dml.base.api.Endpoint
import com.dml.base.model.LoginModel
import com.dml.base.model.LoginRequestModel
import com.dml.base.model.SignUpModel
import com.dml.base.model.SignUpRequestModel
import io.reactivex.Observable

class APIService : BaseService<Endpoint>() {

    override fun getEndpointClass(): Class<Endpoint> = Endpoint::class.java

    fun postSignUpRequest(context: Context, signUpRequestModel: SignUpRequestModel): Observable<SignUpModel>? {
        return getServiceInstance(context)?.postSignUp(signUpRequestModel)
    }

    fun postLoginRequest(context: Context, loginRequestModel: LoginRequestModel): Observable<LoginModel>? {
        return getServiceInstance(context)?.postLogin(loginRequestModel)
    }
}