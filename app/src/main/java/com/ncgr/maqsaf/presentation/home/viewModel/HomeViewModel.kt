package com.ncgr.maqsaf.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _openDialog = MutableStateFlow(false)
    val openDialog = _openDialog.asStateFlow()

    fun openDialog() {
        _openDialog.value = true
    }

    fun closeDialog() {
        _openDialog.value = false
    }
}