package com.ncgr.maqsaf.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.menu.model.Item

data class ItemDto(

	@field:SerializedName("item_image")
	val itemImage: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)

{
	fun toItem(): Item{
		return Item(
            type = type,
			itemImage = itemImage
		)
	}
}
