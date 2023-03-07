package com.ncgr.maqsaf.presentation.serviceProviderTicket.addingTicket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.ticketApi.body.AddTicket
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.ticket.usecase.AddTicketUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddTicketsViewModel @Inject constructor(
    private val getSavedUserUseCase: GetSavedUserUseCase,
    private val addTicketUseCase: AddTicketUseCase,
) : ViewModel() {
    private val _addingTicketStatus = MutableStateFlow<Resource<String>>(Resource.Loading())
    val addingTicketStatus = _addingTicketStatus.asStateFlow()

    private val _openAddingTicketDialog = MutableStateFlow(false)
    val openAddingTicketDialog = _openAddingTicketDialog.asStateFlow()

    private val _description = MutableStateFlow<String?>(null)
    val description = _description.asStateFlow()
    private val _descriptionError = MutableStateFlow<String?>(null)
    val descriptionError = _descriptionError.asStateFlow()

    private val _priority = MutableStateFlow("1")
    val priority = _priority.asStateFlow()
    private val _priorityError = MutableStateFlow<String?>(null)
    val priorityError = _priorityError.asStateFlow()

    private val _finishActivity = MutableStateFlow(false)
    val finishActivity = _finishActivity.asStateFlow()

    private lateinit var userToken: String
    private lateinit var userId: String

    init {
        getSavedUserToken()
    }

    fun setDescription(text: String) {
        _description.value = text
    }

    fun setPriority(text: String) {
        _priority.value = text
    }

    private fun getSavedUserToken() {
        getSavedUserUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    userToken = resource.data.token
                    userId = resource.data.uid
                }
                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun addTicket() {
        if (!checkValidity()) return

        addTicketUseCase(
            AddTicket(
                userId,
                _priority.value,
                _description.value!!
            )
        ).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _addingTicketStatus.value = Resource.Success("Added successfully")
                    delay(2000L)
                    _openAddingTicketDialog.value = false
                    _finishActivity.value = true
                }
                is Resource.Loading -> {
                    _addingTicketStatus.value = Resource.Loading()
                    _openAddingTicketDialog.value = true
                }
                is Resource.Error -> {
                    _addingTicketStatus.value = Resource.Error(resource.apiError)
                    _openAddingTicketDialog.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkValidity(): Boolean {
        var isValid = true
        _addingTicketStatus.value = Resource.Loading()
        _descriptionError.value = null
        _priorityError.value = null

        if (_description.value.isNullOrEmpty()) {
            _descriptionError.value = "Please enter a description"
            openAddingTicketDialog()
            isValid = false
        }
        if (_priority.value.isEmpty()) {
            _priorityError.value = "Please enter a priority"
            openAddingTicketDialog()
            isValid = false
        }

        return isValid
    }

    private fun openAddingTicketDialog() {
        _addingTicketStatus.value = Resource.Error(ApiError(0, "Error form submission"))
        _openAddingTicketDialog.value = true
    }

    fun closeTicketsDialog() {
        _openAddingTicketDialog.value = false
    }
}