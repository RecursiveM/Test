package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ItemRepositoryImp(
    private val itemApi: ItemApi
) : ItemRepository {
    override fun getItems(): Flow<Resource<List<Item>>> = flow {
        emit(Resource.Loading())
        try {
            val response = itemApi.getItems()
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                val items = response.body()?.map { it.toItem() }
                emit(Resource.Success(items!!))
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
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }
}