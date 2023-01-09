package com.ncgr.maqsaf.domain.order.di

import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderUseCaseModule {

    @Singleton
    @Provides
    fun provideGetMyOrderUseCase(
        orderRepository: OrderRepository
    ): GetMyOrderUseCase {
        return GetMyOrderUseCase(orderRepository)
    }
}