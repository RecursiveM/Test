package com.ncgr.maqsaf.data.remote.api

import com.ncgr.maqsaf.data.remote.model.OrderDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderApi {

    @GET("Order?")
    suspend fun getMyOrder(@Query("id") uid: String): Response<List<OrderDto>>
}