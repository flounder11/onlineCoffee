package com.example.coffeeshop.presentation.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.coffeeshop.presentation.about.AboutScreen
import com.example.coffeeshop.presentation.menu.MenuScreen
import com.example.coffeeshop.presentation.order.OrderScreen
import com.example.coffeeshop.presentation.history.HistoryScreen

@Composable
fun MainNav() {
    val navController = rememberNavController()
    val items = listOf(Route.Menu, Route.Order, Route.History, Route.About)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val current = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = current == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(item.title) },
                        icon = { /* можно оставить пустым, чтобы не усложнять */ }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.Menu.route
        ) {
            composable(Route.Menu.route) { MenuScreen(padding) }
            composable(Route.Order.route) { OrderScreen(padding) }
            composable(Route.About.route) { AboutScreen(padding) }
            composable(Route.History.route) { HistoryScreen(padding) }
        }
    }
}
