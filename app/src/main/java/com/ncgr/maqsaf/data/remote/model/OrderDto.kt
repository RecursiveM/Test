package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.order.model.Order

data class OrderDto(

	@field:SerializedName("zone_color")
	val zoneColor: String,

	@field:SerializedName("order_number")
	val orderNumber: Int,

	@field:SerializedName("accepted")
	val accepted: Boolean?,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String
){
	fun toOrder(): Order {
		return Order(
			id = id,
			zoneColor = zoneColor,
			orderNumber = orderNumber,
			accepted = accepted,
		)
	}
}
