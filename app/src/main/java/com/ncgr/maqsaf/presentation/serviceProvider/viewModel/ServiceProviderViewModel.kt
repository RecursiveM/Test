package com.ncgr.maqsaf.presentation.serviceProvider.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.domain.auth.usecase.DeleteSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetUserPreferenceUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SignOutUseCase
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
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val deleteSavedUserUseCase: DeleteSavedUserUseCase,
    private val getUserPreferenceUseCase: GetUserPreferenceUseCase,
) : ViewModel() {

    private val _orderList = MutableStateFlow<Resource<List<OrderListItemDto>>>(Resource.Loading())
    val orderList = _orderList.asStateFlow()

    private val _finishingOrder = MutableStateFlow(false)
    val finishingOrder = _finishingOrder.asStateFlow()

    private val _navigateBackToHome = MutableStateFlow(false)
    val navigateBackToHome = _navigateBackToHome.asStateFlow()

    private lateinit var userToken: String

    init {
        getOrderList()
        getUserToken()
    }

    private fun getUserToken(){
        getUserPreferenceUseCase().onEach { resource ->
            when (resource){
                is Resource.Success -> {
                    userToken = resource.data.token
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

                }
                is Resource.Success -> {
                    deleteSavedUser()
                }
                is Resource.Error -> {

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