package com.ncgr.maqsaf.domain.auth.repository

import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun addUser(uid : String, username : String): Flow<Resource<Boolean>>
    fun addServiceProvider(uid : String, username : String): Flow<Resource<Boolean>>
}