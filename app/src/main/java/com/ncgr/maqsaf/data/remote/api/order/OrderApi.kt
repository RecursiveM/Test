package com.ncgr.maqsaf.data.remote.api.order

import com.ncgr.maqsaf.data.remote.api.order.body.AddOrder
import com.ncgr.maqsaf.data.remote.api.order.body.ChangeOrderState
import com.ncgr.maqsaf.data.remote.model.MyOrderDto
import com.ncgr.maqsaf.data.remote.model.OrderDto
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderApi {

    @GET("rest/v1/Order?select=*,have(*)&order=order_number.desc&")
    suspend fun getMyOrder(@Query("user_id") uid:String): Response<List<MyOrderDto>>

    @GET("rest/v1/Order?select=*,have(*,Item(*))&order=order_number&order_state=neq.Cancelled&order_state=neq.Finished&order_state=neq.Rejected")
    suspend fun getOrderList(): Response<List<OrderListItemDto>>

    @POST("rest/v1/Order")
    suspend fun addOrder(@Body body: AddOrder) : Response<Void>

    @PATCH("rest/v1/Order?")
    suspend fun changeOrderState(@Query("id") id: String, @Body body: ChangeOrderState) : Response<Void>
}