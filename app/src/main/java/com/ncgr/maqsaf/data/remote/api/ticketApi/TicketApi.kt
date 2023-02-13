package com.ncgr.maqsaf.data.remote.api.ticketApi

import com.ncgr.maqsaf.data.remote.api.ticketApi.body.AddTicket
import com.ncgr.maqsaf.data.remote.model.TicketDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TicketApi {

    @GET("rest/v1/Ticket?select=*&")
    suspend fun getMyTickets(@Query("user_id") uid: String): Response<List<TicketDto>>

    @POST("rest/v1/Ticket")
    suspend fun addTicket(@Body body: AddTicket): Response<Void>
}
