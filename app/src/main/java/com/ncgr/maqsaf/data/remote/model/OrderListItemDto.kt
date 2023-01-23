package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName

data class HaveItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("withMilk")
	val withMilk: Boolean,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("sugar_amount")
	val sugarAmount: Int,

	@field:SerializedName("Item")
	val item: Item
)

data class OrderListItemDto(

	@field:SerializedName("zone_color")
	val zoneColor: String,

	@field:SerializedName("order_number")
	val orderNumber: Int,

	@field:SerializedName("have")
	val have: List<HaveItem>,

	@field:SerializedName("order_state")
	val orderState: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String
)

data class Item(

	@field:SerializedName("item_image")
	val itemImage: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)
