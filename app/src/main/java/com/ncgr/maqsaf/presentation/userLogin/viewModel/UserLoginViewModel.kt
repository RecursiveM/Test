package com.ncgr.maqsaf.presentation.userLogin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.usecase.GetUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.LoginUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getMyOrderUseCase: GetMyOrderUseCase
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

    private val _navigateToOrderDetailsActivity = MutableStateFlow(false)
    val navigateToOrderDetailsActivity = _navigateToOrderDetailsActivity.asStateFlow()

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
                    if (it.data.isProvider) {
                        _loginStatus.value = Resource.Error(
                            ApiError(
                                0,
                                "الحساب مسجل كموفر خدمة قم بالتسجيل كموفر خدمة\nThis account is a service provider, please go to service provider login page to continue"
                            )
                        )
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
                    checkIfUserHasPendingOrder(resource.user.id)
                }

                is Resource.Error -> {
                    _loginStatus.value = Resource.Error(it.apiError)
                    _openLoginDialog.value = true
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun checkIfUserHasPendingOrder(uid: String) {
        getMyOrderUseCase(uid).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.data.isNotEmpty()) {
                        if (resource.data[0].orderState == "Pending") {
                            _loginStatus.value = Resource.Success("تم تسجيل الدخول")
                            delay(2000L)
                            _navigateToOrderDetailsActivity.value = true
                        } else {
                            _loginStatus.value = Resource.Success("تم تسجيل الدخول")
                            delay(2000L)
                            _navigateToUserActivity.value = true
                        }
                    } else {
                        _navigateToUserActivity.value = true
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    _navigateToUserActivity.value = true
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
            _phoneNumberError.value = "الرجاء كتابة الرقم الوظيفي بشكل صحيح ويتكون من 5 او 6 ارقام"
            openLoginDialog()
            isValid = false

        }
        if (_passwordText.value.isNullOrEmpty() || _passwordText.value!!.length <= 5) {
            _passwordTextError.value = "يجب ان تكون كلمه المرور 6 حقول فاكثر"
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