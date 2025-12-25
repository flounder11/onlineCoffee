package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CoffeeRepository
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val repo: CoffeeRepository
) {
    suspend operator fun invoke() = repo.getMenu()
}
