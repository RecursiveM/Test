package com.ncgr.maqsaf.data.remote.api.di

import com.ncgr.maqsaf.data.remote.api.interceptor.SupabaseApiInterceptor
import com.ncgr.maqsaf.data.utils.SupabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseClientModule {

    @Singleton
    @Provides
    fun provideRetrofitSupabaseClient(
        supabaseApiInterceptor: SupabaseApiInterceptor
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(SupabaseConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().addInterceptor(supabaseApiInterceptor).build())
    }
}