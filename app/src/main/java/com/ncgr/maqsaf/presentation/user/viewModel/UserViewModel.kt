package com.ncgr.maqsaf.presentation.user.viewModel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.usecase.SendOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.Yellow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val sendOrderUseCase: SendOrderUseCase
) : ViewModel() {

    private val _itemList = MutableSharedFlow<Resource<List<Item>>>()
    val itemList = _itemList.asSharedFlow()

    private val _selectedZoneColor = MutableStateFlow("")
    val selectedZoneColor = _selectedZoneColor.asStateFlow()

    private val _selectedItem = MutableStateFlow("")
    val selectedItem = _selectedItem.asStateFlow()

    private val _itemSelectionError = MutableStateFlow<String?>(null)
    val itemSelectionError = _itemSelectionError.asStateFlow()

    private val _zoneColorSelectionError = MutableStateFlow<String?>(null)
    val zoneColorSelectionError = _zoneColorSelectionError.asStateFlow()

    private val _orderStatus = MutableStateFlow<Resource<String>>(Resource.Loading())
    val orderStatus = _orderStatus.asStateFlow()

    private val _showOrderDialog = MutableStateFlow(false)
    val showOrderDialog = _showOrderDialog.asStateFlow()

    private val _navigateToOrderDetails = MutableStateFlow(false)
    val navigateToOrderDetails = _navigateToOrderDetails.asStateFlow()

    private val _orderDetails = MutableSharedFlow<Order>()
    val orderDetails = _orderDetails.asSharedFlow()

    init {
        getItemList()
    }

    private fun getItemList() {
        getItemsUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _itemList.emit(resource)
                }
                is Resource.Success -> {
                    _itemList.emit(resource)
                }
                is Resource.Error -> {
                    _itemList.emit(resource)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun sendMyOrder() {
        showOrderDialog()
        if (!checkIfOrderIsReady()) {
            _orderStatus.value = Resource.Error(ApiError(0,"Error sending order"))
            return
        }

        sendOrderUseCase(selectedItem = _selectedItem.value, selectedZoneColor = _selectedZoneColor.value)
            .onEach { resource ->
            when (resource){
                is Resource.Loading ->{
                    _orderStatus.value = Resource.Loading()
                }
                is Resource.Success -> {
                    _orderStatus.value = Resource.Success("تم إرسال طلبك")
                    _orderDetails.emit(resource.data)
                    delay(2000L)
                    _navigateToOrderDetails.value = true
                    closeOrderDialog()
                }
                is Resource.Error -> {
                    _orderStatus.value = Resource.Error(resource.apiError)
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun checkIfOrderIsReady(): Boolean {
        var ready = true
        _itemSelectionError.value = null
        _zoneColorSelectionError.value = null

        if (_selectedZoneColor.value == "") {
            _zoneColorSelectionError.value = "الرجاء اختيار لون مكانك"
            ready = false
        }
        if (_selectedItem.value == "") {
            _itemSelectionError.value = "الرجاء اختيار طلبك"
            ready = false
        }

        return ready
    }

    fun closeOrderDialog(){
        _orderStatus.value = Resource.Loading()
        _showOrderDialog.value = false
    }

    private fun showOrderDialog(){
        _orderStatus.value = Resource.Loading()
        _showOrderDialog.value = true
    }

    fun changeZoneColor(color: Color) {
        viewModelScope.launch {
            if (color == Blue) {
                _selectedZoneColor.emit("Blue")
                return@launch
            }

            if (color == Green) {
                _selectedZoneColor.emit("Green")
                return@launch
            }

            if (color == Yellow) {
                _selectedZoneColor.emit("Yellow")
                return@launch
            }

            if (color == Red) {
                _selectedZoneColor.emit("Red")
                return@launch
            }

            _selectedZoneColor.emit("")
        }
    }

    fun changeItem(item: String) {
        viewModelScope.launch {
            _selectedItem.emit(item)
        }
    }
}