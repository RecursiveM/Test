package com.ncgr.maqsaf.data.remote.api.item

import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.data.remote.model.ItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ItemApi {

    @GET("rest/v1/Item?select=*")
    suspend fun getItems() : Response<List<ItemDto>>

    @POST("rest/v1/have")
    suspend fun addItem(@Body body : AddItem) : Response<Void>
}
