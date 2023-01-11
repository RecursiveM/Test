package com.ncgr.maqsaf.domain.menu.di

import com.ncgr.maqsaf.domain.menu.repository.OrderRepository
import com.ncgr.maqsaf.domain.menu.usecase.DeleteOrderUseCase
import com.ncgr.maqsaf.domain.menu.usecase.GetMyOrderUseCase
import com.ncgr.maqsaf.domain.menu.usecase.SendOrderUseCase
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

    @Singleton
    @Provides
    fun provideDeleteOrderUseCase(
        orderRepository: OrderRepository
    ): DeleteOrderUseCase {
        return DeleteOrderUseCase(orderRepository)
    }
}