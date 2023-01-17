package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllOrdersUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(): Flow<Resource<List<OrderListItemDto>>> =
        orderRepository.getOrderList()
}