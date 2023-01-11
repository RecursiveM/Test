package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName

data class HaveItem(

	@field:SerializedName("Item")
	val item: Item
)

data class MenuDtoItem(

	@field:SerializedName("zone_color")
	val zoneColor: String,

	@field:SerializedName("order_number")
	val orderNumber: Int,

	@field:SerializedName("have")
	val have: List<HaveItem>,

	@field:SerializedName("accepted")
	val accepted: Any,

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
