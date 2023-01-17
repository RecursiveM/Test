package com.ncgr.maqsaf.domain.auth.usecase

import com.ncgr.maqsaf.domain.auth.repository.UserRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddUserUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(uid:String, username: String): Flow<Resource<Boolean>> {
        return userRepository.addUser(uid = uid, username = username)
    }
}