package com.ncgr.maqsaf.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.usecase.CheckUserByTokenUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetUserUseCase
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSavedUserUseCase: GetSavedUserUseCase,
    private val checkUserByTokenUseCase: CheckUserByTokenUseCase,
    private val getMyOrderUseCase: GetMyOrderUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _navigateToOrderDetailsActivity = MutableStateFlow(false)
    val navigateToOrderDetailsActivity = _navigateToOrderDetailsActivity.asStateFlow()

    private val _navigateToUserActivity = MutableStateFlow(false)
    val navigateToUserActivity = _navigateToUserActivity.asStateFlow()

    private val _navigateToServiceProviderActivity = MutableStateFlow(false)
    val navigateToServiceProviderActivity = _navigateToServiceProviderActivity.asStateFlow()

    private val _checkingUser = MutableStateFlow<Resource<Boolean>>(Resource.Loading())
    val checkingUser = _checkingUser.asStateFlow()


    private lateinit var userToken: String
    private lateinit var userId: String

    init {
        getSavedUserToken()
    }

    private fun getSavedUserToken() {
        getSavedUserUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    userToken = resource.data.token
                    userId = resource.data.uid

                    checkIfAlreadySignedIn()
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    _checkingUser.value = Resource.Error(ApiError(0,""))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkUserRole(uid:String) {
        getUserUseCase(uid).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data.isProvider){
                        _checkingUser.value = Resource.Success(true)
                        _navigateToServiceProviderActivity.value = true
                    }
                    else {
                        checkIfUserHasPendingOrder()
                    }
                }

                is Resource.Error -> {
                    _checkingUser.value = Resource.Error(ApiError(0,""))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkIfAlreadySignedIn(){
        checkUserByTokenUseCase(userToken).onEach {resource ->
            when (resource) {
                is Resource.Success -> {
                    checkUserRole(userId)
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    _checkingUser.value = Resource.Error(ApiError(0,""))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkIfUserHasPendingOrder(){
        getMyOrderUseCase(userId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _checkingUser.value = Resource.Success(true)
                    if (resource.data.isNotEmpty()){
                        if (resource.data[0].orderState == "Pending"){

                            _navigateToOrderDetailsActivity.value = true
                        }
                        else{
                            _navigateToUserActivity.value = true
                        }
                    }else{
                        _navigateToUserActivity.value = true
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    _checkingUser.value = Resource.Error(ApiError(0,""))
                }
            }
        }.launchIn(viewModelScope)
    }
}