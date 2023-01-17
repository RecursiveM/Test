package com.ncgr.maqsaf.domain.auth.repository

import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun registerWithPhoneNumber(phone: String, password: String): Flow<Resource<UserToken>>
    fun loginWithPhoneNumber(phone: String, password: String): Flow<Resource<UserToken>>
    fun signOut(token: String): Flow<Resource<Boolean>>
}