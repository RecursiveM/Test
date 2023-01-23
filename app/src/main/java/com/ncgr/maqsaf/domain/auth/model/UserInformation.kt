package com.ncgr.maqsaf.domain.auth.model


data class UserInformation(
    val id: String,
    val username: String,
    val isProvider: Boolean,
)
