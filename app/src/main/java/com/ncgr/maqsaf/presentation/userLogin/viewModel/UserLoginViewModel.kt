package com.ncgr.maqsaf.presentation.userLogin.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.usecase.LoginUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val loginUseCase: LoginUseCase,
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
                    saveUserWhenSuccess(resource.data)
                }

                is Resource.Error -> {
                    _loginStatus.value = Resource.Error(resource.apiError)
                    _openLoginDialog.value = true
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun saveUserWhenSuccess(resource: UserToken) {

        saveUserUseCase(token = resource.token).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    _loginStatus.value = Resource.Success("تم تسجيل الدخول")
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

        if (_phoneNumber.value.isNullOrEmpty() || !Patterns.PHONE.matcher(_phoneNumber.value!!)
                .matches()
        ) {
            _phoneNumberError.value = "الرجاء كتابة رقم جوال صحيح"
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