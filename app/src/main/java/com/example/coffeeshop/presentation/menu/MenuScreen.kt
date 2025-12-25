package com.example.coffeeshop.presentation.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coffeeshop.presentation.common.UiState

@Composable
fun MenuScreen(paddingValues: PaddingValues, vm: MenuViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Меню кофейни", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        when (state) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> {
                Text((state as UiState.Error).message)
                Spacer(Modifier.height(8.dp))
                Button(onClick = vm::load) { Text("Повторить") }
            }
            is UiState.Success -> {
                val list = (state as UiState.Success).data
                list.forEach { coffee ->
                    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(coffee.name)
                            Text("${coffee.price} ₽")
                        }
                    }
                }
            }
        }
    }
}
