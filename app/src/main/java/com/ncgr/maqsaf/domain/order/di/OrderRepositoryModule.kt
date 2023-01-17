package com.ncgr.maqsaf.domain.order.di

import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.remote.api.order.OrderApi
import com.ncgr.maqsaf.data.repository.OrderRepositoryImp
import com.ncgr.maqsaf.domain.order.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderRepositoryModule {

    @Singleton
    @Provides
    fun provideOrderRepository(
        orderApi: OrderApi,
        itemApi: ItemApi
    ): OrderRepository {
        return OrderRepositoryImp(orderApi,itemApi)
    }
}