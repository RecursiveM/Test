package com.ncgr.maqsaf.presentation.serviceProviderRegister.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.usecase.AddServiceProviderUseCase
import com.ncgr.maqsaf.domain.auth.usecase.RegisterUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ServiceProviderRegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val addServiceProviderUseCase: AddServiceProviderUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username = _username.asStateFlow()
    private val _usernameError = MutableStateFlow<String?>(null)
    val usernameError = _usernameError.asStateFlow()

    private val _phoneNumber = MutableStateFlow<String?>(null)
    val phoneNumber = _phoneNumber.asStateFlow()
    private val _phoneNumberError = MutableStateFlow<String?>(null)
    val phoneNumberError = _phoneNumberError.asStateFlow()

    private val _passwordText = MutableStateFlow<String?>(null)
    val passwordText = _passwordText.asStateFlow()
    private val _passwordTextError = MutableStateFlow<String?>(null)
    val passwordTextError = _passwordTextError.asStateFlow()

    private val _registerStatus = MutableStateFlow<Resource<String>>(Resource.Loading())
    val registerStatus = _registerStatus.asStateFlow()

    private val _openRegisterDialog = MutableStateFlow(false)
    val openRegisterDialog = _openRegisterDialog.asStateFlow()

    private val _navigateToHomeActivity = MutableStateFlow(false)
    val navigateToServiceProviderActivity = _navigateToHomeActivity.asStateFlow()

    fun setUsername(text: String) {
        _username.value = text
    }

    fun setPhoneNumber(text: String) {
        _phoneNumber.value = text
    }

    fun setPasswordText(text: String) {
        _passwordText.value = text
    }

    fun register() {
        if (!checkValidity()) return

        registerUseCase(_phoneNumber.value!!, _passwordText.value!!).onEach { resource ->

            when (resource) {
                is Resource.Loading -> {
                    _registerStatus.value = Resource.Loading()
                    _openRegisterDialog.value = true
                }

                is Resource.Success -> {
                    addUserWhenSuccess(resource.data)
                }

                is Resource.Error -> {
                    _registerStatus.value = Resource.Error(resource.apiError)
                    _openRegisterDialog.value = true
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun addUserWhenSuccess(resource: UserToken) {

        addServiceProviderUseCase(resource.user.id, _username.value!!).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    saveUserUseCase(token = resource.token, uid = resource.user.id).launchIn(viewModelScope)
                    _registerStatus.value = Resource.Success("Registered successfully")
                    delay(2000L)
                    _navigateToHomeActivity.value = true
                }

                is Resource.Error -> {
                    _registerStatus.value = Resource.Error(it.apiError)
                    _openRegisterDialog.value = true
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun checkValidity(): Boolean {
        var isValid = true
        _registerStatus.value = Resource.Loading()
        _phoneNumberError.value = null
        _passwordTextError.value = null
        _usernameError.value = null

        if (_username.value.isNullOrEmpty()) {
            _usernameError.value = "Please enter your username"
            openRegisterDialog()
            isValid = false
        }
        if (_phoneNumber.value.isNullOrEmpty() || _phoneNumber.value!!.length != 6) {
            _phoneNumberError.value = "Your employee ID must be 6 characters"
            openRegisterDialog()
            isValid = false
        }
        if (_passwordText.value.isNullOrEmpty() || _passwordText.value!!.length <= 5) {
            _passwordTextError.value = "Your password must be at least 6 characters"
            openRegisterDialog()
            isValid = false
        }

        return isValid
    }

    private fun openRegisterDialog() {
        _registerStatus.value = Resource.Error(ApiError(0, "Error form submission"))
        _openRegisterDialog.value = true
    }

    fun closeRegisterDialog() {
        _openRegisterDialog.value = false
    }
}