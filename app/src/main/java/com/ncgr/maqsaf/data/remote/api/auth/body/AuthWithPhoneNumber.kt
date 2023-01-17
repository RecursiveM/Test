package com.ncgr.maqsaf.data.remote.api.auth.body

data class AuthWithPhoneNumber(
    val phone : String,
    val password : String,
)