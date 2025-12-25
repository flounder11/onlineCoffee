package com.example.coffeeshop.data.mapper

import com.example.coffeeshop.data.dto.CoffeeDto
import com.example.coffeeshop.domain.model.Coffee

fun CoffeeDto.toDomain(): Coffee = Coffee(id, name, price)
