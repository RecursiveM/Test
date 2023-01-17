package com.ncgr.maqsaf.domain.auth.di

import com.ncgr.maqsaf.data.remote.api.auth.AuthApi
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.repository.AuthRepositoryImp
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.data.repository.SharedPreferenceRepositoryImp
import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.domain.auth.repository.SharedPreferenceRepository
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceRepositoryModule {

    @Singleton
    @Provides
    fun provideSharedPreferenceRepository(): SharedPreferenceRepository {
        return SharedPreferenceRepositoryImp()
    }
}