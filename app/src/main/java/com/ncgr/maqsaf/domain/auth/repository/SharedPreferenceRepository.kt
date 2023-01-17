package com.ncgr.maqsaf.domain.auth.repository

import com.ncgr.maqsaf.domain.auth.model.UserPreference
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SharedPreferenceRepository {
    fun saveUser(token: String): Flow<Resource<Boolean>>
    fun deleteSavedUser(): Flow<Resource<Boolean>>
    fun getUserPreference(): Flow<Resource<UserPreference>>
}