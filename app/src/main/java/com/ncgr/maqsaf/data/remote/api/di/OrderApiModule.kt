package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.order.OrderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderApiModule {

    @Singleton
    @Provides
    fun provideOrderApi(
        retrofitSupabaseClient: Retrofit.Builder
    ): OrderApi {
        return retrofitSupabaseClient
            .build()
            .create(OrderApi::class.java)
    }
}