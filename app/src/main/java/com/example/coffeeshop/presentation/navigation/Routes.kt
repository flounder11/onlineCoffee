package com.example.coffeeshop.presentation.navigation

sealed class Route(val route: String, val title: String) {
    data object Menu : Route("menu", "Меню")
    data object Order : Route("order", "Заказ")
    data object About : Route("about", "О нас")
    data object History : Route("history", "История")
}
