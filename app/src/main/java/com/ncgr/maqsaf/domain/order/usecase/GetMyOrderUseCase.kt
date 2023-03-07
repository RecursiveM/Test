package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.data.remote.model.MyOrderDto
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetMyOrderUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(uid: String): Flow<Resource<List<MyOrderDto>>> {
        return orderRepository.getMyOrder(uid)
    }

}