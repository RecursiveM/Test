package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.user.UserApi
import com.ncgr.maqsaf.data.remote.api.user.body.AddUser
import com.ncgr.maqsaf.domain.auth.model.UserInformation
import com.ncgr.maqsaf.domain.auth.repository.UserRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImp(
    private val userApi: UserApi
) : UserRepository {

    override fun addUser(uid: String, username: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = userApi.addUser(AddUser(uid, username))
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                emit(Resource.Success(true))
            } else {
                if (response.code() == 409)
                    emit(
                        Resource.Error(
                            ApiError(
                                response.code(),
                                "Username already in use",
                            )
                        )
                    )
                else emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            "Please try again later",
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

    override fun getUser(uid: String): Flow<Resource<UserInformation>>
        = flow {
            emit(Resource.Loading())
            try {
                val response = userApi.getUser("eq.$uid")
                Log.d("TEST", response.toString())
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!![0].toUserInformation()))
                } else {
                        emit(
                            Resource.Error(
                                ApiError(
                                    response.code(),
                                    "User not found",
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


    override fun addServiceProvider(uid: String, username: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = userApi.addUser(AddUser(uid, username, isProvider = true))
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                emit(Resource.Success(true))
            } else {
                if (response.code() == 409)
                    emit(
                        Resource.Error(
                            ApiError(
                                response.code(),
                                "Username already in use",
                            )
                        )
                    )
                else
                emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            "Please try again later",
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