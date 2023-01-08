package com.ncgr.maqsaf.presentation.user.viewModel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.Yellow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _itemList = MutableSharedFlow<List<Item>>()
    val itemList = _itemList.asSharedFlow()

    private val _zoneColor = MutableSharedFlow<String>()
    val zoneColor = _zoneColor.asSharedFlow()

    private val _selectedItem = MutableSharedFlow<String>()
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
                    _itemList.emit(resource.data)
                }
                is Resource.Error -> {
                    Log.d("TEST", resource.apiError.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun changeZoneColor(color: Color) {
        viewModelScope.launch {
            if (color == Blue) {
                _zoneColor.emit("Blue")
                return@launch
            }

            if (color == Green) {
                _zoneColor.emit("Green")
                return@launch
            }

            if (color == Yellow) {
                _zoneColor.emit("Yellow")
                return@launch
            }

            if (color == Red) {
                _zoneColor.emit("Red")
                return@launch
            }

            _zoneColor.emit("")
        }
    }

    fun changeItem(item: String) {
        viewModelScope.launch {
            _selectedItem.emit(item)
        }
    }
}