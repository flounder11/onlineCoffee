package com.example.coffeeshop.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coffeeshop.presentation.common.UiState

@Composable
fun HistoryScreen(
    paddingValues: PaddingValues,
    vm: HistoryViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("История заказов", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        Button(onClick = vm::load) {
            Text("Обновить")
        }

        Spacer(Modifier.height(12.dp))

        when (state) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Text((state as UiState.Error).message)
            }

            is UiState.Success -> {
                val orders = (state as UiState.Success).data

                if (orders.isEmpty()) {
                    Text("Заказов пока нет.")
                } else {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        orders.reversed().forEach { o ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            ) {
                                Column(Modifier.padding(12.dp)) {
                                    Text("Заказ #${o.id}", style = MaterialTheme.typography.titleMedium)
                                    Spacer(Modifier.height(4.dp))
                                    Text("Статус: ${o.status}")
                                    Text("Кофе ID: ${o.coffeeId}, количество: ${o.quantity}")
                                    Spacer(Modifier.height(6.dp))
                                    Text("Имя: ${o.customerName}")
                                    Text("Адрес: ${o.address}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
