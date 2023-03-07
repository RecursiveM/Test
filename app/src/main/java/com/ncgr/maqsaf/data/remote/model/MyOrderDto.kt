package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.order.model.Order

data class MyOrderDto(

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("zone_color")
	val zoneColor: String,

	@field:SerializedName("order_number")
	val orderNumber: Int,

	@field:SerializedName("have")
	val have: List<HaveItems>,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("order_state")
	val orderState: String
){
	fun toOrder(): Order {
		return Order(
			id = id,
			zoneColor = zoneColor,
			orderNumber = orderNumber,
			orderState = orderState
		)
	}
}

data class HaveItems(

	@field:SerializedName("item_id")
	val itemId: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("withMilk")
	val withMilk: Boolean,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("sugar_amount")
	val sugarAmount: Int,

	@field:SerializedName("order_id")
	val orderId: String
)
