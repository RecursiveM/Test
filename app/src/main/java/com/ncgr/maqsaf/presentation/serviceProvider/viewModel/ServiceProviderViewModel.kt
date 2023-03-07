package com.ncgr.maqsaf.presentation.serviceProvider.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.domain.auth.usecase.DeleteSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SignOutUseCase
import com.ncgr.maqsaf.domain.order.usecase.ChangeOrderStateUseCase
import com.ncgr.maqsaf.domain.order.usecase.GetAllOrdersUseCase
import com.ncgr.maqsaf.domain.order.utils.OrderState
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ServiceProviderViewModel @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
    private val changeOrderStateUseCase: ChangeOrderStateUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val deleteSavedUserUseCase: DeleteSavedUserUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase,
) : ViewModel() {

    private val _orderList = MutableStateFlow<Resource<List<OrderListItemDto>>>(Resource.Loading())
    val orderList = _orderList.asStateFlow()

    private val _navigateBackToHome = MutableStateFlow(false)
    val navigateBackToHome = _navigateBackToHome.asStateFlow()

    private val _orderListItem = MutableStateFlow<OrderListItemDto?>(null)
    val orderListItem = _orderListItem.asStateFlow()
    private val _orderListItemIndex = MutableStateFlow(0)
    val orderListItemIndex = _orderListItemIndex.asStateFlow()

    private val _signOutStatus = MutableStateFlow<Resource<String>>(Resource.Loading())
    val signOutStatus = _signOutStatus.asStateFlow()

    private val _showSignOutDialog = MutableStateFlow(false)
    val showSignOutDialog = _showSignOutDialog.asStateFlow()

    private val _openOrderDetails = MutableStateFlow(false)
    val openOrderDetails = _openOrderDetails.asStateFlow()

    private lateinit var userToken: String
    private lateinit var userId: String

    init {
        getOrderList()
        getUserToken()
    }

    private fun getUserToken(){
        getSavedUserUseCase().onEach { resource ->
            when (resource){
                is Resource.Success -> {
                    userToken = resource.data.token
                    userId = resource.data.uid
                }
                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun signOut(){
        signOutUseCase(userToken).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _showSignOutDialog.value = true
                }
                is Resource.Success -> {
                    deleteSavedUser()
                }
                is Resource.Error -> {
                    _signOutStatus.value = Resource.Error(ApiError(0,"Please check your internet connection"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteSavedUser(){
        deleteSavedUserUseCase().onEach {resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _signOutStatus.value = Resource.Success("Signed out successfully")
                    delay(2000L)
                    _showSignOutDialog.value = false
                    _navigateBackToHome.value = true
                }
                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
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

    fun changeOrderState(orderUid: String, orderState: OrderState) {
        _orderList.value = Resource.Loading()
        changeOrderStateUseCase(orderUid,orderState).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _orderList.value = Resource.Loading()
                }
                is Resource.Success -> {
                    getOrderList()
                }
                is Resource.Error -> {
                    _orderList.value = Resource.Error(ApiError(0,"Something went wrong"))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun closeOrderDialog() {
        _signOutStatus.value = Resource.Loading()
        _showSignOutDialog.value = false
    }

    fun openOrderDetailsDialog(orderListItem: OrderListItemDto, index: Int) {
        _orderListItemIndex.value = index
        _orderListItem.value = orderListItem
        _openOrderDetails.value = true
    }

    fun closeOrderDetailsDialog() {
        _openOrderDetails.value = false
    }

    fun refreshOrders() {
       getOrderList()
    }
}