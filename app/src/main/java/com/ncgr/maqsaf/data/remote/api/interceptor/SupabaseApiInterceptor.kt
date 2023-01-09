package com.ncgr.maqsaf.data.remote.api.interceptor

import com.ncgr.maqsaf.data.utils.SupabaseConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SupabaseApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("apikey", SupabaseConstants.API_KEY)
        return chain.proceed(request.build())
    }
}