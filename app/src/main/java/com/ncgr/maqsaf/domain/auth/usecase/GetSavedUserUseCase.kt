package com.ncgr.maqsaf.domain.auth.usecase

import com.ncgr.maqsaf.domain.auth.model.UserPreference
import com.ncgr.maqsaf.domain.auth.repository.SharedPreferenceRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetSavedUserUseCase(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): Flow<Resource<UserPreference>> {
        return sharedPreferenceRepository.getSavedUser()
    }
}