package com.ncgr.maqsaf.data.remote.api

import com.ncgr.maqsaf.data.model.AddOrder
import com.ncgr.maqsaf.data.remote.model.OrderDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderApi {

    @GET("Order?select=*&limit=1&order=order_number.desc")
    suspend fun getMyOrder(): Response<List<OrderDto>>

    @POST("Order")
    suspend fun addOrder(@Body body: AddOrder) : Response<Void>

    @DELETE("Order?")
    suspend fun deleteOrder(@Query("id") id: String) : Response<Void>
}