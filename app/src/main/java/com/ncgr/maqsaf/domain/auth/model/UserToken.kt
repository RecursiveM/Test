package com.ncgr.maqsaf.domain.auth.model

import com.ncgr.maqsaf.data.remote.model.UserInfo

data class UserToken(
    val token: String,
    val user: UserInfo,
)
