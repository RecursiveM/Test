package com.ncgr.maqsaf.presentation.serviceProviderLogin.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.usecase.GetUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.LoginUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ServiceProviderLoginViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _phoneNumber = MutableStateFlow<String?>(null)
    val phoneNumber = _phoneNumber.asStateFlow()
    private val _phoneNumberError = MutableStateFlow<String?>(null)
    val phoneNumberError = _phoneNumberError.asStateFlow()

    private val _passwordText = MutableStateFlow<String?>(null)
    val passwordText = _passwordText.asStateFlow()
    private val _passwordTextError = MutableStateFlow<String?>(null)
    val passwordTextError = _passwordTextError.asStateFlow()

    private val _loginStatus = MutableStateFlow<Resource<String>>(Resource.Loading())
    val loginStatus = _loginStatus.asStateFlow()

    private val _openLoginDialog = MutableStateFlow(false)
    val openLoginDialog = _openLoginDialog.asStateFlow()

    private val _navigateToUserActivity = MutableStateFlow(false)
    val navigateToUserActivity = _navigateToUserActivity.asStateFlow()

    fun setPhoneNumber(text: String) {
        _phoneNumber.value = text
    }

    fun setPasswordText(text: String) {
        _passwordText.value = text
    }

    fun login() {
        if (!checkValidity()) return

        loginUseCase(_phoneNumber.value!!, _passwordText.value!!).onEach { resource ->

            when (resource) {
                is Resource.Loading -> {
                    _loginStatus.value = Resource.Loading()
                    _openLoginDialog.value = true
                }

                is Resource.Success -> {
                    checkUserRole(resource.data)
                }

                is Resource.Error -> {
                    _loginStatus.value = Resource.Error(resource.apiError)
                    _openLoginDialog.value = true
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun checkUserRole(resource: UserToken) {
        getUserUseCase(resource.user.id).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (!it.data.isProvider) {
                        _loginStatus.value = Resource.Error(ApiError(0,"This account is not a service provider, please go to user login page to continue\nالحساب مسجل كمستخدم قم بالتسجيل كمستخدم"))
                        _openLoginDialog.value = true
                    } else {
                        saveUserWhenSuccess(resource = resource)
                    }
                }

                is Resource.Error -> {
                    _loginStatus.value = Resource.Error(it.apiError)
                    _openLoginDialog.value = true
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun saveUserWhenSuccess(resource: UserToken) {

        saveUserUseCase(token = resource.token, uid = resource.user.id).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    _loginStatus.value = Resource.Success("Signed in successfully")
                    delay(2000L)
                    _navigateToUserActivity.value = true
                }

                is Resource.Error -> {
                    _loginStatus.value = Resource.Error(it.apiError)
                    _openLoginDialog.value = true
                }
            }

        }.launchIn(viewModelScope)
    }


    private fun checkValidity(): Boolean {
        var isValid = true
        _loginStatus.value = Resource.Loading()
        _phoneNumberError.value = null
        _passwordTextError.value = null

        if (_phoneNumber.value.isNullOrEmpty() || _phoneNumber.value!!.length < 5) {
            _phoneNumberError.value = "Your employee ID must be at least 5 characters"
            openLoginDialog()
            isValid = false
        }
        if (_passwordText.value.isNullOrEmpty() || _passwordText.value!!.length <= 5) {
            _passwordTextError.value = "Your password must be at least 6 characters"
            openLoginDialog()
            isValid = false
        }

        return isValid
    }

    private fun openLoginDialog() {
        _loginStatus.value = Resource.Error(ApiError(0, "Error form submission"))
        _openLoginDialog.value = true
    }

    fun closeRegisterDialog() {
        _openLoginDialog.value = false
    }
}