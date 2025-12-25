package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.api.CoffeeApi
import com.example.coffeeshop.data.dto.OrderRequestDto
import com.example.coffeeshop.data.dto.OrderDto
import com.example.coffeeshop.data.mapper.*
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.model.OrderResult
import com.example.coffeeshop.domain.repository.CoffeeRepository
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val api: CoffeeApi
) : CoffeeRepository {

    override suspend fun getMenu(): List<Coffee> =
        api.getCoffees().map { it.toDomain() }

    override suspend fun getOrders(): List<Order> =
        api.getOrders().map { it.toDomain() }

    override suspend fun createOrder(
        customerName: String,
        address: String,
        coffeeId: Int,
        quantity: Int
    ): OrderResult =
        api.createOrder(
            OrderRequestDto(customerName, address, coffeeId, quantity)
        ).toDomain()
}
