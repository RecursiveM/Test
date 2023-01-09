package com.ncgr.maqsaf.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _openDialog = MutableStateFlow(false)
    val openDialog = _openDialog.asStateFlow()

    private val _passwordText = MutableStateFlow("")
    val passwordText = _passwordText.asStateFlow()

    private val _openPasswordError = MutableStateFlow(false)
    val openPasswordError = _openPasswordError.asStateFlow()

    fun setPasswordText(text: String){
        _passwordText.value = text
    }

    fun openDialog() {
        _openDialog.value = true
    }

    fun closeDialog() {
        _openDialog.value = false
    }

    fun openPasswordErrorDialog() {
        _openPasswordError.value = true
    }

    fun closePasswordErrorDialog() {
        _openPasswordError.value = false
    }
}