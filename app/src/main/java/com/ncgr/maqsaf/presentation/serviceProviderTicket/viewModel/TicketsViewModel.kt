package com.ncgr.maqsaf.presentation.serviceProviderTicket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.ticket.model.Ticket
import com.ncgr.maqsaf.domain.ticket.usecase.GetMyTicketsUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val getSavedUserUseCase: GetSavedUserUseCase,
    private val getMyTicketsUseCase: GetMyTicketsUseCase
) : ViewModel() {
    private val _ticketList = MutableSharedFlow<Resource<List<Ticket>>>()
    val ticketList = _ticketList.asSharedFlow()

    private lateinit var userToken: String
    private lateinit var userId: String

    init {
        getSavedUserToken()
    }

    private fun getSavedUserToken() {
        getSavedUserUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    userToken = resource.data.token
                    userId = resource.data.uid
                    getTicketList()
                }
                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTicketList() {
        getMyTicketsUseCase(userId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _ticketList.emit(resource)
                }
                is Resource.Success -> {
                    _ticketList.emit(resource)
                }
                is Resource.Error -> {
                    _ticketList.emit(resource)
                }
            }
        }.launchIn(viewModelScope)
    }
}