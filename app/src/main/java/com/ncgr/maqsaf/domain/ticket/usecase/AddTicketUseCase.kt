package com.ncgr.maqsaf.domain.ticket.usecase

import com.ncgr.maqsaf.data.remote.api.ticketApi.body.AddTicket
import com.ncgr.maqsaf.domain.ticket.repository.TicketRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddTicketUseCase(
    private val ticketRepository: TicketRepository
) {
    operator fun invoke(addTicket: AddTicket): Flow<Resource<Boolean>> {
        return ticketRepository.addTicket(addTicket)
    }

}