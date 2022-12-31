package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.ItemApi
import com.ncgr.maqsaf.data.utils.SupabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemApiModule {

    @Singleton
    @Provides
    fun provideItemApi(): ItemApi {
        return Retrofit.Builder()
            .baseUrl(SupabaseConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemApi::class.java)
    }
}