package com.ncgr.maqsaf.domain.menu.model

data class Order(
    val id : String,
    val orderNumber: Int,
    val zoneColor: String,
    val accepted: Boolean?,
)
