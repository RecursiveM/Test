package com.ncgr.maqsaf.presentation.orderDetails.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getMyOrderUseCase: GetMyOrderUseCase
) : ViewModel() {

    private val _myOrder = MutableSharedFlow<Order>()
    val myOrder = _myOrder.asSharedFlow()

    private val _orderNumber = MutableStateFlow(0)
    val orderNumber = _orderNumber.asStateFlow()

    private val _zoneColor = MutableStateFlow("blue")
    val zoneColor = _zoneColor.asStateFlow()

    fun changeZoneColor(color: String){
        _zoneColor.value = color
    }

    fun changeOrderNumber(num: Int){
        _orderNumber.value = num
    }

}