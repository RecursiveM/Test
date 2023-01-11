package com.ncgr.maqsaf.domain.order.di

import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import com.ncgr.maqsaf.domain.order.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.domain.order.usecase.SendOrderUseCase
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

    @Singleton
    @Provides
    fun provideSendOrderUseCase(
        orderRepository: OrderRepository
    ): SendOrderUseCase {
        return SendOrderUseCase(orderRepository)
    }
}