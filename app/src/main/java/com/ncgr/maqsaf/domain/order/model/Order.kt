package com.ncgr.maqsaf.domain.order.model

data class Order(
    val id : String,
    val orderNumber: Int,
    val zoneColor: String,
    val orderState: String,
)
