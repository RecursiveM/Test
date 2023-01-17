package com.ncgr.maqsaf.presentation.serviceProvider.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.domain.order.usecase.DeleteOrderUseCase
import com.ncgr.maqsaf.domain.order.usecase.GetAllOrdersUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ServiceProviderViewModel @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase
) : ViewModel() {

    private val _orderList = MutableStateFlow<Resource<List<OrderListItemDto>>>(Resource.Loading())
    val orderList = _orderList.asStateFlow()

    private val _finishingOrder = MutableStateFlow(false)
    val finishingOrder = _finishingOrder.asStateFlow()

    init {
        getOrderList()
    }

    private fun getOrderList() {
        getAllOrdersUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _orderList.value = resource
                }
                is Resource.Success -> {
                    _orderList.value = resource
                }
                is Resource.Error -> {
                    _orderList.value = resource
                }
            }
        }.launchIn(viewModelScope)
    }

    fun finishOrder(orderUid: String) {
        deleteOrderUseCase(orderUid).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _finishingOrder.value = true
                }
                is Resource.Success -> {
                    _finishingOrder.value = false
                    getOrderList()
                }
                is Resource.Error -> {
                    _finishingOrder.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun refreshOrders() {
       getOrderList()
    }
}