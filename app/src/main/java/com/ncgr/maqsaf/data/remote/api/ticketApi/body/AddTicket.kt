package com.ncgr.maqsaf.data.remote.api.ticketApi.body

data class AddTicket(
    val user_id: String,
    val priority: String,
    val description: String,
)
