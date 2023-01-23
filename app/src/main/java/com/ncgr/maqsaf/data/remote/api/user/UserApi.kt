package com.ncgr.maqsaf.data.remote.api.user

import com.ncgr.maqsaf.data.remote.api.user.body.AddUser
import com.ncgr.maqsaf.data.remote.model.UserInfoItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("rest/v1/User")
    suspend fun addUser(@Body body: AddUser): Response<Void>

    @GET("rest/v1/User?")
    suspend fun getUser(@Query("id") id: String): Response<List<UserInfoItemDto>>
}