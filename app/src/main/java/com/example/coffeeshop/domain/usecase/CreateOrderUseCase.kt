package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CoffeeRepository
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val repo: CoffeeRepository
) {
    suspend operator fun invoke(
        customerName: String,
        address: String,
        coffeeId: Int,
        quantity: Int
    ) = repo.createOrder(customerName, address, coffeeId, quantity)
}
