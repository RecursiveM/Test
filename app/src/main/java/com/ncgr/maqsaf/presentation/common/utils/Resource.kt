package com.ncgr.maqsaf.presentation.common.utils

import com.ncgr.maqsaf.data.model.ApiError

sealed class Resource<T: Any> {
    data class Success<T: Any>(val data: T): Resource<T>()
    data class Error<T: Any>(val apiError: ApiError): Resource<T>()
    class Loading<T: Any>: Resource<T>()
}
