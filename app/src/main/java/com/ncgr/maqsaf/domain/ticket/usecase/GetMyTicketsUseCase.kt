package com.ncgr.maqsaf.domain.ticket.usecase

import com.ncgr.maqsaf.domain.ticket.model.Ticket
import com.ncgr.maqsaf.domain.ticket.repository.TicketRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetMyTicketsUseCase(
    private val ticketRepository: TicketRepository
) {
    operator fun invoke(uid: String): Flow<Resource<List<Ticket>>> {
        return ticketRepository.getMyTickets(uid)
    }

}