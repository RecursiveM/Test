package com.ncgr.maqsaf.presentation.user.viewModel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.domain.auth.usecase.DeleteSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.GetSavedUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SaveUserUseCase
import com.ncgr.maqsaf.domain.auth.usecase.SignOutUseCase
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.domain.order.usecase.AddOrderUseCase
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.user.composable.ListItem
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.Yellow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val addOrderUseCase: AddOrderUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val deleteSavedUserUseCase: DeleteSavedUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private val _selectedZoneColor = MutableStateFlow("")
    val selectedZoneColor = _selectedZoneColor.asStateFlow()

    private val _selectedItems = MutableStateFlow<SnapshotStateList<AddItem>>(SnapshotStateList())
    val selectedItems: StateFlow<SnapshotStateList<AddItem>> = _selectedItems.asStateFlow()

    private val _itemSelectionError = MutableStateFlow<String?>(null)
    val itemSelectionError = _itemSelectionError.asStateFlow()

    private val _zoneColorSelectionError = MutableStateFlow<String?>(null)
    val zoneColorSelectionError = _zoneColorSelectionError.asStateFlow()

    private val _orderStatus = MutableStateFlow<Resource<String>>(Resource.Loading())
    val orderStatus = _orderStatus.asStateFlow()

    private val _showOrderDialog = MutableStateFlow(false)
    val showOrderDialog = _showOrderDialog.asStateFlow()

    private val _navigateToOrderDetails = MutableStateFlow(false)
    val navigateToOrderDetails = _navigateToOrderDetails.asStateFlow()

    private val _navigateBackToHome = MutableStateFlow(false)
    val navigateBackToHome = _navigateBackToHome.asStateFlow()

    private val _orderDetails = MutableSharedFlow<Order>()
    val orderDetails = _orderDetails.asSharedFlow()

    private val _openItemDetails = MutableStateFlow(false)
    val openItemDetails = _openItemDetails.asStateFlow()

    private val _itemDetails = MutableStateFlow("")
    val itemDetails = _itemDetails.asStateFlow()

    private val _drinkType = MutableStateFlow("")
    val drinkType = _drinkType.asStateFlow()

    private val _withMilk = MutableStateFlow(false)
    val withMilk = _withMilk.asStateFlow()

    private val _sugarAmount = MutableStateFlow(0)
    val sugarAmount = _sugarAmount.asStateFlow()

    private val _showItemDetailsDialogError = MutableStateFlow(false)
    val showItemDetailsDialogError = _showItemDetailsDialogError.asStateFlow()

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
                    _selectedZoneColor.value = resource.data.ZoneColor
                }
                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun signOut() {
        signOutUseCase(userToken).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _showOrderDialog.value = true
                }
                is Resource.Success -> {
                    deleteSavedUser()
                }
                is Resource.Error -> {
                    _orderStatus.value = Resource.Error(ApiError(0, "تاكد من اتصالك بالإنترنت"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteSavedUser() {
        deleteSavedUserUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _orderStatus.value = Resource.Success("تم تسجيل الخروج بنجاح")
                    delay(2000L)
                    _showOrderDialog.value = false
                    _navigateBackToHome.value = true
                }
                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun addMyOrder() {
        showOrderDialog()
        if (!checkIfOrderIsReady()) {
            _orderStatus.value = Resource.Error(ApiError(0, "Error sending order"))
            return
        }

        addOrderUseCase(
            selectedItems = _selectedItems.value,
            selectedZoneColor = _selectedZoneColor.value,
            userId = userId,
        )
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _orderStatus.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        _orderStatus.value = Resource.Success("تم إرسال طلبك")
                        _orderDetails.emit(resource.data)
                        delay(2000L)
                        saveUserUseCase(userToken, userId, _selectedZoneColor.value).launchIn(
                            viewModelScope
                        )
                        _navigateToOrderDetails.value = true
                        closeOrderDialog()
                    }
                    is Resource.Error -> {
                        _orderStatus.value = Resource.Error(resource.apiError)
                    }
                }

            }.launchIn(viewModelScope)
    }

    private fun checkIfOrderIsReady(): Boolean {
        var ready = true
        _itemSelectionError.value = null
        _zoneColorSelectionError.value = null

        if (_selectedZoneColor.value == "") {
            _zoneColorSelectionError.value = "الرجاء اختيار لون مكانك"
            ready = false
        }
        if (_selectedItems.value.isEmpty()) {
            _itemSelectionError.value = "الرجاء اختيار طلبك"
            ready = false
        }

        return ready
    }

    fun closeOrderDialog() {
        _orderStatus.value = Resource.Loading()
        _showOrderDialog.value = false
        _showItemDetailsDialogError.value = false
    }

    private fun showOrderDialog() {
        _orderStatus.value = Resource.Loading()
        _showOrderDialog.value = true
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

    fun addItem(item: AddItem) {
        if (_selectedItems.value.size == 2) {
            _showItemDetailsDialogError.value = true
            return
        }
        _selectedItems.value.add(item)
        closeItemDetails()
    }

    fun openItemDetails(item: String) {

        //reset selected
        _drinkType.value = ""
        _withMilk.value = false
        _sugarAmount.value = 0

        when (item) {
            "coffee" -> {
                _itemDetails.value = "coffee"
                _openItemDetails.value = true
            }
            "tea" -> {
                _itemDetails.value = "tea"
                _openItemDetails.value = true
            }
            "water"
            -> {
                _itemDetails.value = "water"
                _openItemDetails.value = true
            }
        }
    }

    fun closeItemDetails() {
        _openItemDetails.value = false
    }

    fun checkIfItemSelected(item: ListItem): MutableStateFlow<Int> {
        val selected = MutableStateFlow(0)
        for (itemInList in _selectedItems.value) {
            if (item.type == itemInList.type) selected.value++
            if (itemInList.type == "نعناع" && item.type == "tea") selected.value++
            if (itemInList.type == "حبق" && item.type == "tea") selected.value++
            if (itemInList.type == "شاهي" && item.type == "tea") selected.value++
            if (itemInList.type == "نسكافيه" && item.type == "coffee") selected.value++
            if (itemInList.type == "امريكية" && item.type == "coffee") selected.value++
            if (itemInList.type == "قهوة" && item.type == "coffee") selected.value++
            if (itemInList.type == "ماء" && item.type == "water") selected.value++
        }
        return selected
    }

    fun deleteSelectedItem(item: AddItem) {
        _selectedItems.value.remove(item)
    }

    fun changeWithMilk() {
        viewModelScope.launch {
            _withMilk.value = !_withMilk.value
        }
    }

    fun changeSugarAmount(amount: Int) {
        viewModelScope.launch {
            _sugarAmount.value = amount
        }
    }

    fun changeType(type: String) {
        viewModelScope.launch {
            _drinkType.value = type
        }
    }
}