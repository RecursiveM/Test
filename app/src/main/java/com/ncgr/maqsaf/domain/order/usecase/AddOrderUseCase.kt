package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddOrderUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(selectedItems: List<AddItem>, selectedZoneColor: String,userId: String): Flow<Resource<Order>> =
        orderRepository.addOrder(selectedItems, selectedZoneColor,userId)
}