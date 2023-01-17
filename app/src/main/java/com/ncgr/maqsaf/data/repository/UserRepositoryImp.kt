package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.user.UserApi
import com.ncgr.maqsaf.data.remote.api.user.body.AddUser
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
                emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            response.message(),
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
            val response = userApi.addUser(AddUser(uid, username,isProvider = true))
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                emit(Resource.Success(true))
            } else {
                emit(
                    Resource.Error(
                        ApiError(
                            response.code(),
                            response.message(),
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