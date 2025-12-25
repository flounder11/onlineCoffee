package com.example.coffeeshop.data.dto

data class OrderRequestDto(
    val customerName: String,
    val address: String,
    val coffeeId: Int,
    val quantity: Int
)
