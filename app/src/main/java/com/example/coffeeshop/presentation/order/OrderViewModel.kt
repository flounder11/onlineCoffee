package com.example.coffeeshop.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.usecase.CreateOrderUseCase
import com.example.coffeeshop.domain.usecase.GetMenuUseCase
import com.example.coffeeshop.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrderUi(
    val menuState: UiState<List<Coffee>> = UiState.Loading,
    val customerName: String = "",
    val address: String = "",
    val selectedCoffeeId: Int? = null,
    val quantity: Int = 1,
    val submitState: UiState<String>? = null
)

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getMenu: GetMenuUseCase,
    private val createOrder: CreateOrderUseCase
) : ViewModel() {

    private val _ui = MutableStateFlow(OrderUi())
    val ui: StateFlow<OrderUi> = _ui

    init { loadMenu() }

    fun loadMenu() {
        _ui.value = _ui.value.copy(menuState = UiState.Loading)
        viewModelScope.launch {
            try {
                val menu = getMenu()
                val defaultId = menu.firstOrNull()?.id
                _ui.value = _ui.value.copy(
                    menuState = UiState.Success(menu),
                    selectedCoffeeId = _ui.value.selectedCoffeeId ?: defaultId
                )
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(menuState = UiState.Error("Не удалось загрузить меню"))
            }
        }
    }

    fun onNameChange(v: String) { _ui.value = _ui.value.copy(customerName = v) }
    fun onAddressChange(v: String) { _ui.value = _ui.value.copy(address = v) }
    fun onCoffeeChange(id: Int) { _ui.value = _ui.value.copy(selectedCoffeeId = id) }
    fun onQuantityChange(q: Int) { _ui.value = _ui.value.copy(quantity = q.coerceAtLeast(1)) }

    fun submit() {
        val s = _ui.value
        val coffeeId = s.selectedCoffeeId ?: return

        _ui.value = s.copy(submitState = UiState.Loading)

        viewModelScope.launch {
            try {
                val result = createOrder(s.customerName, s.address, coffeeId, s.quantity)
                _ui.value = _ui.value.copy(submitState = UiState.Success("Заказ №${result.orderId}: ${result.status}"))
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(submitState = UiState.Error("Ошибка отправки заказа"))
            }
        }
    }
}
