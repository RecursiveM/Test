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

    private val _itemList = MutableSharedFlow<Resource<List<Item>>>()
    val itemList = _itemList.asSharedFlow()

    private val _selectedZoneColor = MutableSharedFlow<String>()
    val selectedZoneColor = _selectedZoneColor.asSharedFlow()

    private val _selectedItem = MutableSharedFlow<String>()
    val selectedItem = _selectedItem.asSharedFlow()

    init {
        getItemList()
    }

    private fun getItemList() {
        getItemsUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _itemList.emit(resource)
                }
                is Resource.Success -> {
                    _itemList.emit(resource)
                }
                is Resource.Error -> {
                    _itemList.emit(resource)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun changeZoneColor(color: Color) {
        viewModelScope.launch {
            if (color == Blue) {
                _selectedZoneColor.emit("Blue")
                return@launch
            }

            if (color == Green) {
                _selectedZoneColor.emit("Green")
                return@launch
            }

            if (color == Yellow) {
                _selectedZoneColor.emit("Yellow")
                return@launch
            }

            if (color == Red) {
                _selectedZoneColor.emit("Red")
                return@launch
            }

            _selectedZoneColor.emit("")
        }
    }

    fun changeItem(item: String) {
        viewModelScope.launch {
            _selectedItem.emit(item)
        }
    }
}