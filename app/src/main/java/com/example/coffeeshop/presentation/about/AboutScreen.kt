package com.example.coffeeshop.presentation.about

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment

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
        Text("CoffeeShop — лучши прилолжение для заказа кофэ.")
        Spacer(Modifier.height(8.dp))
        Text("Слоган:", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Покупайте кофе у нас и у вас будет все - деньги, тачки, админки!")

//        Image(
//            painter = painterResource(id = R.drawable.kruto),
//            contentDescription = "Это я",
//            modifier = Modifier
//                .size(160.dp)
//                .padding(bottom = 16.dp)
//        )
    }
}
