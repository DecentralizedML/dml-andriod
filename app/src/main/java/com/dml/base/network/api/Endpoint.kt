package com.dml.base.network.api

import com.dml.base.network.model.UserLoginRequest
import com.dml.base.network.model.UserLoginResponse
import com.dml.base.network.model.UserSignUpRequest
import com.dml.base.network.model.UserSignUpResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface Endpoint {

    @POST("/api/users")
    fun postUserSignUp(@Body body: UserSignUpRequest): Observable<UserSignUpResponse>

    @PUT("/api/users")
    fun updateUser(@Body body: UserSignUpRequest): Observable<UserSignUpResponse>

    @POST("/api/users/authenticate")
    fun postUserLogin(@Body body: UserLoginRequest): Observable<UserLoginResponse>
}