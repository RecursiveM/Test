package com.ncgr.maqsaf.domain.auth.usecase

import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(phone: String, password: String): Flow<Resource<UserToken>> {
        return authRepository.loginWithPhoneNumber(phone, password)
    }
}