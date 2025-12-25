package com.example.coffeeshop.presentation.order

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coffeeshop.presentation.common.UiState

@Composable
fun OrderScreen(paddingValues: PaddingValues, vm: OrderViewModel = hiltViewModel()) {
    val ui by vm.ui.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Оформление заказа", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = ui.customerName,
            onValueChange = vm::onNameChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Имя") }
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = ui.address,
            onValueChange = vm::onAddressChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Адрес") }
        )
        Spacer(Modifier.height(12.dp))

        when (val ms = ui.menuState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> {
                Text(ms.message)
                Spacer(Modifier.height(8.dp))
                Button(onClick = vm::loadMenu) { Text("Повторить") }
            }
            is UiState.Success -> {
                val menu = ms.data
                Text("Выбери кофе:")
                Spacer(Modifier.height(6.dp))

                // простой список кнопок (без усложнений)
                menu.forEach { c ->
                    val selected = ui.selectedCoffeeId == c.id
                    OutlinedButton(
                        onClick = { vm.onCoffeeChange(c.id) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)
                    ) {
                        Text(if (selected) "✓ ${c.name} — ${c.price} ₽" else "${c.name} — ${c.price} ₽")
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Количество: ${ui.quantity}")
            Row {
                Button(onClick = { vm.onQuantityChange(ui.quantity - 1) }) { Text("-") }
                Spacer(Modifier.width(8.dp))
                Button(onClick = { vm.onQuantityChange(ui.quantity + 1) }) { Text("+") }
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = vm::submit,
            modifier = Modifier.fillMaxWidth(),
            enabled = ui.customerName.isNotBlank() && ui.address.isNotBlank()
        ) {
            Text("Отправить заказ")
        }

        Spacer(Modifier.height(12.dp))
        when (val ss = ui.submitState) {
            null -> Unit
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> Text(ss.message)
            is UiState.Success -> Text(ss.data)
        }
    }
}
