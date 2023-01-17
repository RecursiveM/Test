package com.ncgr.maqsaf.domain.auth.usecase

import com.ncgr.maqsaf.domain.auth.repository.SharedPreferenceRepository
import com.ncgr.maqsaf.domain.auth.repository.UserRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class DeleteSavedUserUseCase(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): Flow<Resource<Boolean>> {
        return sharedPreferenceRepository.deleteSavedUser()
    }
}