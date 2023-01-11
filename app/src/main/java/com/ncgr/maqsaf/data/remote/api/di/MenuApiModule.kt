package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.MenuApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MenuApiModule {

    @Singleton
    @Provides
    fun provideItemApi(
        retrofitSupabaseClient: Retrofit.Builder
    ): MenuApi {
        return retrofitSupabaseClient
            .build()
            .create(MenuApi::class.java)
    }
}