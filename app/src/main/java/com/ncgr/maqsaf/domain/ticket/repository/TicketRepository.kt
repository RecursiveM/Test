package com.ncgr.maqsaf.domain.ticket.repository

import com.ncgr.maqsaf.data.remote.api.ticketApi.body.AddTicket
import com.ncgr.maqsaf.domain.ticket.model.Ticket
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getMyTickets(uid: String): Flow<Resource<List<Ticket>>>
    fun addTicket(addTicket:AddTicket): Flow<Resource<Boolean>>
}