package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.auth.AuthApi
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthApiModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        retrofitSupabaseClient: Retrofit.Builder
    ): AuthApi {
        return retrofitSupabaseClient
            .build()
            .create(AuthApi::class.java)
    }
}