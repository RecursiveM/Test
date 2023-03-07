package com.ncgr.maqsaf.presentation.orderDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.remote.model.MyOrderDto
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getMyOrderUseCase: GetMyOrderUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase,
) : ViewModel() {

    private val _myOrders = MutableStateFlow<Resource<List<MyOrderDto>>>(Resource.Loading())
    val myOrders = _myOrders.asStateFlow()

    private val _navigateToUserActivity = MutableStateFlow(false)
    val navigateToUserActivity = _navigateToUserActivity.asStateFlow()

    private lateinit var userToken: String
    private lateinit var userId: String

    init {
        getUserToken()
    }

    private fun getUserToken() {
        getSavedUserUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    userToken = resource.data.token
                    userId = resource.data.uid

                    getMyOrder()
                }
                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMyOrder() {
        getMyOrderUseCase(userId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _myOrders.value = resource
                }
                is Resource.Success -> {
                    _myOrders.value = resource
                }
                is Resource.Error -> {
                    _myOrders.value = resource
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navToUserActivity() {
        _navigateToUserActivity.value = true
    }
}