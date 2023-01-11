package com.ncgr.maqsaf.data.remote.api

import com.ncgr.maqsaf.data.remote.model.MenuDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface MenuApi {

    @GET("Order?select=*,have(Item(*))")
    suspend fun getAllOrders(): Response<List<MenuDtoItem>>
}