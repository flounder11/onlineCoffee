package com.example.coffeeshop.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repo: CoffeeRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Order>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Order>>> = _state

    init {
        load()
    }

    fun load() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            try {
                val orders = repo.getOrders()
                _state.value = UiState.Success(orders)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Ошибка загрузки заказов")
            }
        }
    }
}
