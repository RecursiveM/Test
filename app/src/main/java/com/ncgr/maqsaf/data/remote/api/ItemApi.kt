package com.ncgr.maqsaf.data.remote.api

import com.ncgr.maqsaf.data.model.AddItem
import com.ncgr.maqsaf.data.remote.model.ItemDto
import com.ncgr.maqsaf.data.utils.SupabaseConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ItemApi {

    @GET("Item?select=*")
    suspend fun getItems() : Response<List<ItemDto>>

    @POST("have")
    suspend fun addItem(@Body body : AddItem) : Response<Void>
}
