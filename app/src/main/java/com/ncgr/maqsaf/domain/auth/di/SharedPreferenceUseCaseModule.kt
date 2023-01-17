package com.ncgr.maqsaf.domain.auth.di

import com.ncgr.maqsaf.data.remote.api.auth.AuthApi
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.repository.AuthRepositoryImp
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.domain.auth.repository.SharedPreferenceRepository
import com.ncgr.maqsaf.domain.auth.usecase.DeleteSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetUserPreferenceUseCase
import com.ncgr.maqsaf.domain.auth.usecase.RegisterUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceUseCaseModule {

    @Singleton
    @Provides
    fun provideSaveUserUseCase(
        sharedPreferenceRepository: SharedPreferenceRepository
    ): SaveUserUseCase {
        return SaveUserUseCase(sharedPreferenceRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedUserUseCase(
        sharedPreferenceRepository: SharedPreferenceRepository
    ): DeleteSavedUserUseCase {
        return DeleteSavedUserUseCase(sharedPreferenceRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserPreferenceUseCase(
        sharedPreferenceRepository: SharedPreferenceRepository
    ): GetUserPreferenceUseCase {
        return GetUserPreferenceUseCase(sharedPreferenceRepository)
    }
}