package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class DeleteOrderUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(orderUid:String) : Flow<Resource<Boolean>> =
        orderRepository.finishOrder(orderUid)
}