package com.ncgr.maqsaf.presentation.userRegister.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.domain.auth.model.UserToken
import com.ncgr.maqsaf.domain.auth.usecase.AddUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.RegisterUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserRegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val addUserUseCase: AddUserUseCase,
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

    private val _navigateToUserActivity = MutableStateFlow(false)
    val navigateToUserActivity = _navigateToUserActivity.asStateFlow()

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

        addUserUseCase(resource.user.id, _username.value!!).onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    saveUserUseCase(token = resource.token, uid = resource.user.id).launchIn(
                        viewModelScope
                    )
                    _registerStatus.value = Resource.Success("تم التسجيل بنجاح")
                    delay(2000L)
                    _navigateToUserActivity.value = true
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

            _usernameError.value = "الرجاء كتابة اسم المستخدم"
            openRegisterDialog()
            isValid = false
        }
        if (_phoneNumber.value.isNullOrEmpty() || _phoneNumber.value!!.length < 5) {
            _phoneNumberError.value = "الرجاء كتابة الرقم الوظيفي بشكل صحيح ويتكون من 5 او 6 ارقام"
            openRegisterDialog()
            isValid = false

        }
        if (_passwordText.value.isNullOrEmpty() || _passwordText.value!!.length <= 5) {
            _passwordTextError.value = "يجب ان تكون كلمه المرور 6 حقول فاكثر"
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