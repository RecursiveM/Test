package com.ncgr.maqsaf.data.remote.model

import com.google.gson.annotations.SerializedName
import com.ncgr.maqsaf.domain.auth.model.UserInformation

data class UserInfoItemDto(

	@field:SerializedName("isProvider")
	val isProvider: Boolean,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("username")
	val username: String
){
	fun toUserInformation(): UserInformation {
		return UserInformation(
			id = id,
			username = username,
			isProvider = isProvider,
		)
	}
}
