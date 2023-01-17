package com.ncgr.maqsaf.data.remote.api.user

import com.ncgr.maqsaf.data.remote.api.user.body.AddUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("rest/v1/User")
    suspend fun addUser(@Body body: AddUser): Response<Void>
}