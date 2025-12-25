package com.example.coffeeshop.data.api

import com.example.coffeeshop.data.dto.CoffeeDto
import com.example.coffeeshop.data.dto.OrderRequestDto
import com.example.coffeeshop.data.dto.OrderResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoffeeApi {
    @GET("coffees")
    suspend fun getCoffees(): List<CoffeeDto>

    @POST("orders")
    suspend fun createOrder(@Body body: OrderRequestDto): OrderResponseDto

    @GET("orders")
    suspend fun getOrders(): List<OrderDto>;

}
