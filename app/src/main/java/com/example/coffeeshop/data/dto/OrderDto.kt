package com.example.coffeeshop.data.dto

data class OrderDto(
    val id: Long,
    val customerName: String,
    val address: String,
    val coffeeId: Int,
    val quantity: Int,
    val status: String
)