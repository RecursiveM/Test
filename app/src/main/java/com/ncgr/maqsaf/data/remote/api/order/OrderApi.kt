package com.ncgr.maqsaf.data.remote.api.order

import com.ncgr.maqsaf.data.remote.api.order.body.AddOrder
import com.ncgr.maqsaf.data.remote.model.OrderDto
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderApi {

    @GET("rest/v1/Order?select=*&limit=1&order=order_number.desc")
    suspend fun getMyOrder(): Response<List<OrderDto>>

    @GET("rest/v1/Order?select=*,have(Item(*))")
    suspend fun getOrderList(): Response<List<OrderListItemDto>>

    @POST("rest/v1/Order")
    suspend fun addOrder(@Body body: AddOrder) : Response<Void>

    @DELETE("rest/v1/Order?")
    suspend fun deleteOrder(@Query("id") id: String) : Response<Void>
}