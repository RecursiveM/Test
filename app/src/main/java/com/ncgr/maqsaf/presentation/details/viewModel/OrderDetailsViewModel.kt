package com.ncgr.maqsaf.presentation.details.viewModel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.ui.theme.Red
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getMyOrderUseCase: GetMyOrderUseCase
) : ViewModel() {

    private val _myOrder = MutableSharedFlow<Order>()
    val myOrder = _myOrder.asSharedFlow()

    //Fake Data
    val randomNumber = listOf(0,1,2,3,4,5,6,7,8,9,10,222,555).random()
    private val _zoneColor = MutableStateFlow("blue")
    val zoneColor = _zoneColor.asStateFlow()
    fun changeZoneColor(color: String){
        _zoneColor.value = color
    }

    private fun getMyOrder() {
        getMyOrderUseCase("f4541678-5467-46f0-a7a1-2b2a503379c9").onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("TEST", "Loading Order Details")
                }
                is Resource.Success -> {
                    _myOrder.emit(resource.data[0])
                }
                is Resource.Error -> {
                    Log.d("TEST", resource.apiError.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }

}