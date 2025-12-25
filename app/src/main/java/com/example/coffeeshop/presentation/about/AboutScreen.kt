package com.example.coffeeshop.presentation.about

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("О нас", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Text("CoffeeShop — учебное приложение онлайн-кофейни.")
        Spacer(Modifier.height(8.dp))
        Text("Функции: просмотр меню, оформление заказа, информация о кофейне.")
    }
}
