package com.ncgr.maqsaf.domain.menu.usecase

import com.ncgr.maqsaf.domain.menu.model.Order
import com.ncgr.maqsaf.domain.menu.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class SendOrderUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(selectedItem: String, selectedZoneColor: String): Flow<Resource<Order>> =
        orderRepository.addOrder(selectedItem, selectedZoneColor)
}