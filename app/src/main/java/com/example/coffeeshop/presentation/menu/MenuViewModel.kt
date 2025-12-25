package com.example.coffeeshop.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.usecase.GetMenuUseCase
import com.example.coffeeshop.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenu: GetMenuUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Coffee>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Coffee>>> = _state

    init { load() }

    fun load() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            try {
                _state.value = UiState.Success(getMenu())
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Ошибка загрузки меню")
            }
        }
    }
}
