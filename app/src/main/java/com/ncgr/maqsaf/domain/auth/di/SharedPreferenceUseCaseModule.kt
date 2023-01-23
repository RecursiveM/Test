package com.ncgr.maqsaf.domain.auth.di

import com.ncgr.maqsaf.domain.auth.repository.SharedPreferenceRepository
import com.ncgr.maqsaf.domain.auth.usecase.DeleteSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
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
    ): GetSavedUserUseCase {
        return GetSavedUserUseCase(sharedPreferenceRepository)
    }
}