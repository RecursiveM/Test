package com.ncgr.maqsaf.presentation.customer.viewModel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.ui.theme.blue
import com.ncgr.maqsaf.ui.theme.green
import com.ncgr.maqsaf.ui.theme.red
import com.ncgr.maqsaf.ui.theme.yellow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _itemList = MutableStateFlow(listOf<Item>())
    val itemList = _itemList.asStateFlow()

    private val _zoneColor = MutableStateFlow("Blue")
    val zoneColor = _zoneColor.asStateFlow()

    private val _selectedItem = MutableSharedFlow<String?>()
    val selectedItem = _selectedItem.asSharedFlow()

    init {
        getItems()
    }

    private fun getItems() {
        getItemsUseCase().onEach { resource ->

            when (resource) {
                is Resource.Loading -> {
                    Log.d("TEST", resource.toString())
                }
                is Resource.Success -> {
                    Log.d("TEST", resource.data.size.toString())
                    _itemList.value = resource.data
                }
                is Resource.Error -> {
                    Log.d("TEST", resource.apiError.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun changeZoneColor(color: Color) {
        if (color == blue) {
            _zoneColor.value = "Blue"
            return
        }

        if (color == green) {
            _zoneColor.value = "Green"
            return
        }

        if (color == yellow) {
            _zoneColor.value = "Yellow"
            return
        }

        if (color == red) {
            _zoneColor.value = "Red"
            return
        }

        _zoneColor.value = ""
    }

    fun changeItem(item: String) {
        viewModelScope.launch {
            _selectedItem.emit("")
        }
    }
}