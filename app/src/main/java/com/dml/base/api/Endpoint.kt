package com.dml.base.api

import com.dml.base.model.UserLoginModel
import com.dml.base.model.UserLoginRequestModel
import com.dml.base.model.UserSignUpModel
import com.dml.base.model.UserSignUpRequestModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface Endpoint {

    @POST("/api/users")
    fun postUserSignUp(@Body body: UserSignUpRequestModel): Observable<UserSignUpModel>

    @POST("/api/users/authenticate")
    fun postUserLogin(@Body body: UserLoginRequestModel): Observable<UserLoginModel>
}