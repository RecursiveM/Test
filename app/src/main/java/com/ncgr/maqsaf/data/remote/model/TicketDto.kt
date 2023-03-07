package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.ticket.model.Ticket

data class TicketDto(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("ticket_number")
    val ticketNumber: Int,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("priority")
    val priority: String,

    @field:SerializedName("respond")
    val respond: String?
) {
    fun toTicket() = Ticket(
        id = id,
        description = description,
        priority = priority,
        respond = respond,
        ticketNumber = ticketNumber
    )
}
