package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemApiModule {

    @Singleton
    @Provides
    fun provideItemApi(
        retrofitSupabaseClient: Retrofit.Builder
    ): ItemApi {
        return retrofitSupabaseClient
            .build()
            .create(ItemApi::class.java)
    }
}