package com.ncgr.maqsaf.domain.auth.di

import com.ncgr.maqsaf.data.remote.api.auth.AuthApi
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.remote.api.user.UserApi
import com.ncgr.maqsaf.data.repository.AuthRepositoryImp
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.data.repository.UserRepositoryImp
import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.domain.auth.repository.UserRepository
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userApi: UserApi
    ): UserRepository {
        return UserRepositoryImp(userApi)
    }
}