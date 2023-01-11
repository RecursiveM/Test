package com.ncgr.maqsaf.domain.order.repository

import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getMyOrder(uid: String): Flow<Resource<List<Order>>>
    fun sendOrder(selectedItem:String,selectedZoneColor:String): Flow<Resource<Order>>
}