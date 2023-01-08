package com.ncgr.maqsaf.presentation.customer.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _itemList = MutableStateFlow(listOf<Item>())
    val itemList = _itemList.asStateFlow()

    init {
        getItems()
    }

    private fun getItems(){
        getItemsUseCase().onEach { resource ->

            when(resource){
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

}