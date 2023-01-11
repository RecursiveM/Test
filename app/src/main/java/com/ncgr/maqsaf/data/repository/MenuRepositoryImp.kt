package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.MenuApi
import com.ncgr.maqsaf.data.remote.model.MenuDtoItem
import com.ncgr.maqsaf.domain.menu.repository.MenuRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MenuRepositoryImp(
    private val menuApi: MenuApi,
) : MenuRepository {
    override fun getAllOrders(): Flow<Resource<List<MenuDtoItem>>> = flow {
        emit(Resource.Loading())
        try {
            val response = menuApi.getAllOrders()
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                val allOrders = response.body()!!
                emit(Resource.Success(allOrders))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(ApiError(e.code(), e.message())))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    ApiError(
                        e.hashCode(),
                        "Please check your internet connection and try again"
                    )
                )
            )
        }
        catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }
}