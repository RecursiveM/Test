package com.ncgr.maqsaf.data.remote.api.auth

import com.ncgr.maqsaf.data.remote.api.auth.body.AuthWithPhoneNumber
import com.ncgr.maqsaf.data.remote.model.UserTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/v1/signup")
    suspend fun registerWithPhoneNumber(@Body body: AuthWithPhoneNumber): Response<UserTokenDto>

    @POST("auth/v1/token?grant_type=password")
    suspend fun loginWithPhoneNumber(@Body body: AuthWithPhoneNumber): Response<UserTokenDto>

    @POST("auth/v1/logout")
    suspend fun signOut(@Header("Authorization") token:String): Response<Void>
}