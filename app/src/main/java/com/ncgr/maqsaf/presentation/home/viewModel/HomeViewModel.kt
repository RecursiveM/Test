package com.ncgr.maqsaf.presentation.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel(){

    private val _itemsInfo = MutableStateFlow<List<Item>>(emptyList())
    val itemsInfo = _itemsInfo.asStateFlow()

    init {
        getItems()
    }

    private fun getItems(){
        viewModelScope.launch {
            getItemsUseCase().onEach { resource ->
            when(resource) {
                is Resource.Loading -> {
                    Log.d("TEST", "Loading")
                }
                is Resource.Success -> {
                    _itemsInfo.value =  resource.data
                }
                is Resource.Error -> {
                    Log.d("TEST", resource.apiError.errorMessage)
                }
            }
            }.launchIn(viewModelScope)
        }
    }
}
