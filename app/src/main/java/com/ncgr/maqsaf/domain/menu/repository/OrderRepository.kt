package com.ncgr.maqsaf.domain.menu.repository

import com.ncgr.maqsaf.domain.menu.model.Order
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getMyOrder(uid: String): Flow<Resource<List<Order>>>
    fun addOrder(selectedItem:String, selectedZoneColor:String): Flow<Resource<Order>>
    fun finishOrder(orderUid :String): Flow<Resource<Boolean>>
}