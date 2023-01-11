package com.ncgr.maqsaf.domain.menu.usecase

import com.ncgr.maqsaf.domain.menu.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class DeleteOrderUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(orderUid:String) : Flow<Resource<Boolean>> =
        orderRepository.finishOrder(orderUid)
}