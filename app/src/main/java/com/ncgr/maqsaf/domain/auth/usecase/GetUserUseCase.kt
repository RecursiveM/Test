package com.ncgr.maqsaf.domain.auth.usecase

import com.ncgr.maqsaf.data.remote.model.UserInfo
import com.ncgr.maqsaf.domain.auth.model.UserInformation
import com.ncgr.maqsaf.domain.auth.repository.UserRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(uid:String): Flow<Resource<UserInformation>> {
        return userRepository.getUser(uid = uid)
    }
}