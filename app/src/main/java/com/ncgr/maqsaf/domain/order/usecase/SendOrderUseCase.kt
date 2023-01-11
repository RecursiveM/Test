package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class SendOrderUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(selectedItem: String, selectedZoneColor: String): Flow<Resource<Order>> =
        orderRepository.sendOrder(selectedItem, selectedZoneColor)
}