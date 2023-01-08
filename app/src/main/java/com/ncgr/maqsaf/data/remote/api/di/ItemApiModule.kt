package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.ItemApi
import com.ncgr.maqsaf.data.remote.api.interceptor.ItemApiInterceptor
import com.ncgr.maqsaf.data.utils.SupabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemApiModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(
        itemApiInterceptor: ItemApiInterceptor
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(SupabaseConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(itemApiInterceptor).build())
    }

    @Singleton
    @Provides
    fun provideItemApi(
        retrofitClient: Retrofit.Builder
    ): ItemApi {
        return retrofitClient
            .build()
            .create(ItemApi::class.java)
    }
}