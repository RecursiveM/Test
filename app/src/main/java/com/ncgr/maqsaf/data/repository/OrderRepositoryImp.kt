package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.data.remote.api.order.body.AddOrder
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.remote.api.order.OrderApi
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.data.utils.ItemIdsConstants
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class OrderRepositoryImp(
    private val orderApi: OrderApi,
    private val itemApi: ItemApi,
) : OrderRepository {

    override fun getOrderList(): Flow<Resource<List<OrderListItemDto>>> = flow {
        emit(Resource.Loading())
        try {
            val response = orderApi.getOrderList()
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

    override fun getMyOrder(uid: String): Flow<Resource<List<Order>>> = flow {
        emit(Resource.Loading())
        try {
            TODO("Will be Updated When We have account")
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

    override fun addOrder(
        selectedItem: String,
        selectedZoneColor: String
    ): Flow<Resource<Order>> = flow {
        emit(Resource.Loading())
        try {
            val orderResponse = orderApi.addOrder(AddOrder(selectedZoneColor))
            Log.d("TEST", orderResponse.toString())
            if (orderResponse.isSuccessful) {
                val orderId = orderApi.getMyOrder()
                Log.d("TEST", orderId.toString())
                if (orderId.isSuccessful) {
                    val itemResponse = itemApi.addItem(
                        AddItem(
                            orderId.body()!![0].id,
                            when (selectedItem) {
                                "tea" -> ItemIdsConstants.TEA
                                "water" -> ItemIdsConstants.WATER
                                else -> ItemIdsConstants.COFFEE
                            }
                        )
                    )
                    Log.d("TEST", itemResponse.toString())
                    emit(Resource.Success(orderId.body()!![0].toOrder()))
                }

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

    override fun finishOrder(orderUid: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = orderApi.deleteOrder("eq.$orderUid")
            if (response.isSuccessful)
                emit(Resource.Success(true))
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