package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.OrderApi
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class OrderRepositoryImp(
    private val orderApi: OrderApi
): OrderRepository {
    override fun getMyOrder(uid: String): Flow<Resource<List<Order>>> = flow {
        emit(Resource.Loading())
        try {
            val response = orderApi.getMyOrder("eq.{$uid}")
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                val orders = response.body()?.map { it.toOrder() }
                emit(Resource.Success(orders!!))
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