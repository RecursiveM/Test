package com.ncgr.maqsaf.domain.ticket.model

data class Ticket(
    val id: String,
    val ticketNumber: Int,
    val description: String,
    val priority: String,
    val respond: String?
)
