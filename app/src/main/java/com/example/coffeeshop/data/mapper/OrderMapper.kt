package com.example.coffeeshop.data.mapper

import com.example.coffeeshop.data.dto.OrderResponseDto
import com.example.coffeeshop.domain.model.OrderResult

fun OrderResponseDto.toDomain(): OrderResult = OrderResult(orderId, status)
