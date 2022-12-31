package com.ncgr.maqsaf.data.remote.api

import com.ncgr.maqsaf.data.remote.dto.ItemDto
import com.ncgr.maqsaf.data.utils.SupabaseConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ItemApi {

    @Headers(
        "apikey:${SupabaseConstants.API_KEY}",
    )
    @GET("v1/Item?select=*")
    suspend fun getItems() : Response<List<ItemDto>>
}