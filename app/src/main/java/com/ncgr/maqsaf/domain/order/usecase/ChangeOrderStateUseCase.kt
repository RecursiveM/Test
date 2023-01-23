package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.domain.order.utils.OrderState
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class ChangeOrderStateUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(orderUid:String, orderState: OrderState) : Flow<Resource<Boolean>> =
        orderRepository.changeOrderState(orderUid,orderState)
}