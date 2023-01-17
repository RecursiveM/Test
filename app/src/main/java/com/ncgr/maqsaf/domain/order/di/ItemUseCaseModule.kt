package com.ncgr.maqsaf.domain.order.di

import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import com.ncgr.maqsaf.domain.order.usecase.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ItemUseCaseModule {

    @Singleton
    @Provides
    fun provideGetItemUseCase(
        itemRepository: ItemRepository
    ): GetItemsUseCase {
        return GetItemsUseCase(itemRepository)
    }
}