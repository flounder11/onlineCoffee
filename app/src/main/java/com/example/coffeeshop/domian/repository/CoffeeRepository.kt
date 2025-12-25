package com.example.coffeeshop.domain.repository

import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.OrderResult

interface CoffeeRepository {
    suspend fun getMenu(): List<Coffee>
    suspend fun createOrder(
        customerName: String,
        address: String,
        coffeeId: Int,
        quantity: Int
    ): OrderResult
}
