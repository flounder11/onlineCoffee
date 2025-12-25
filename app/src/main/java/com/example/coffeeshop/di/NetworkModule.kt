package com.example.coffeeshop.di

import com.example.coffeeshop.data.api.CoffeeApi
import com.example.coffeeshop.data.repository.CoffeeRepositoryImpl
import com.example.coffeeshop.domain.repository.CoffeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            // ВАЖНО: тут будет адрес твоего Spring-сервера
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): CoffeeApi =
        retrofit.create(CoffeeApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: CoffeeApi): CoffeeRepository =
        CoffeeRepositoryImpl(api)
}
