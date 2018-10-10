package com.dml.base.api

import com.dml.base.model.LoginModel
import com.dml.base.model.LoginRequestModel
import com.dml.base.model.SignUpModel
import com.dml.base.model.SignUpRequestModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface Endpoint {

    @POST("/api/users")
    fun postSignUp(@Body body: SignUpRequestModel): Observable<SignUpModel>

    @POST("/api/users/authenticate")
    fun postLogin(@Body body: LoginRequestModel): Observable<LoginModel>
}