package com.ncgr.maqsaf.domain.order.repository

import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.utils.OrderState
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getMyOrder(uid: String): Flow<Resource<List<Order>>>
    fun getOrderList(): Flow<Resource<List<OrderListItemDto>>>
    fun addOrder(selectedItems: List<AddItem>, selectedZoneColor:String,uid: String): Flow<Resource<Order>>
    fun changeOrderState(orderUid :String, orderState: OrderState): Flow<Resource<Boolean>>
}