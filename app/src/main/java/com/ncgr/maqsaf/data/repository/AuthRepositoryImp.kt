package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.auth.AuthApi
import com.ncgr.maqsaf.data.remote.api.auth.body.AuthWithPhoneNumber
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.repository.AuthRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImp(
    private val authApi: AuthApi
) : AuthRepository {
    override fun registerWithPhoneNumber(
        phone: String,
        password: String
    ): Flow<Resource<UserToken>> = flow {
        emit(Resource.Loading())
        try {
            val response = authApi.registerWithPhoneNumber(AuthWithPhoneNumber(phone, password))
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                val userToken = response.body()!!.toUserToken()
                emit(Resource.Success(userToken))
            } else {
                emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            "تاكد من اضافة رمز المنطقة للرقم"
                        )
                    )
                )
            }
        } catch (e: HttpException) {
            emit(Resource.Error(ApiError(e.code(), e.toString())))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    ApiError(
                        e.hashCode(),
                        "Please check your internet connection and try again"
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }

    override fun loginWithPhoneNumber(phone: String, password: String): Flow<Resource<UserToken>> = flow {
        emit(Resource.Loading())
        try {
            val response = authApi.loginWithPhoneNumber(AuthWithPhoneNumber(phone, password))
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                val userToken = response.body()!!.toUserToken()
                emit(Resource.Success(userToken))
            } else {
                emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            "تاكد من اضافة رمز المنطقة للرقم"
                        )
                    )
                )
            }
        } catch (e: HttpException) {
            emit(Resource.Error(ApiError(e.code(), e.toString())))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    ApiError(
                        e.hashCode(),
                        "Please check your internet connection and try again"
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }

    override fun signOut(token:String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = authApi.signOut("Bearer $token")
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                emit(Resource.Success(true))
            } else {
                emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            response.message()
                        )
                    )
                )
            }
        } catch (e: HttpException) {
            emit(Resource.Error(ApiError(e.code(), e.toString())))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    ApiError(
                        e.hashCode(),
                        "Please check your internet connection and try again"
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }
}