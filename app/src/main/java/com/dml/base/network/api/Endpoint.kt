package com.dml.base.network.api

import com.dml.base.network.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface Endpoint {

    @POST("/api/users")
    fun postUserSignUp(@Body body: UserSignUpRequest): Observable<UserSignUpResponse>

    @PUT("/api/users")
    fun updateUser(@Body body: UserUpdateRequest): Observable<UserUpdateResponse>

    @POST("/api/users/authenticate")
    fun postUserLogin(@Body body: UserLoginRequest): Observable<UserLoginResponse>
}