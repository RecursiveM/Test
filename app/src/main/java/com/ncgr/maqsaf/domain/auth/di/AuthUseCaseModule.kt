package com.ncgr.maqsaf.domain.auth.di

import com.ncgr.maqsaf.data.remote.api.auth.AuthApi
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.repository.AuthRepositoryImp
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.domain.auth.usecase.CheckUserByTokenUseCase
import com.ncgr.maqsaf.domain.auth.usecase.LoginUseCase
import com.ncgr.maqsaf.domain.auth.usecase.RegisterUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SignOutUseCase
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {

    @Singleton
    @Provides
    fun provideRegisterUseCase(
        authRepository: AuthRepository
    ): RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideSignOutUseCase(
        authRepository: AuthRepository
    ): SignOutUseCase {
        return SignOutUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideCheckUserByTokenUseCase(
        authRepository: AuthRepository
    ): CheckUserByTokenUseCase {
        return CheckUserByTokenUseCase(authRepository)
    }
}