package com.ncgr.maqsaf.domain.auth.usecase

import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class SignOutUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(token: String) : Flow<Resource<Boolean>>{
        return authRepository.signOut(token)
    }
}