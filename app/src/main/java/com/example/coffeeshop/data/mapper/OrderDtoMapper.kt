package com.example.coffeeshop.data.mapper

import com.example.coffeeshop.data.dto.OrderDto
import com.example.coffeeshop.domain.model.Order

fun OrderDto.toDomain(): Order = Order(
    id = id,
    customerName = customerName,
    address = address,
    coffeeId = coffeeId,
    quantity = quantity,
    status = status
)
