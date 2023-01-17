package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.order.model.Item

data class ItemDto(

	@field:SerializedName("item_image")
	val imageUrl: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)

{
	fun toItem(): Item {
		return Item(
			id = id,
			type = type,
			imageUrl = imageUrl
		)
	}
}
