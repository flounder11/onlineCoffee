package com.example.coffeeshop.domain.model

data class Order(
    val id: Long,
    val customerName: String,
    val address: String,
    val coffeeId: Int,
    val quantity: Int,
    val status: String
)
