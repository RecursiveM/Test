package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.ticketApi.TicketApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TicketApiModule {

    @Singleton
    @Provides
    fun provideTicketApi(
        retrofitSupabaseClient: Retrofit.Builder
    ): TicketApi {
        return retrofitSupabaseClient
            .build()
            .create(TicketApi::class.java)
    }
}