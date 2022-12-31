package com.ncgr.maqsaf.domain.menu.di

import com.ncgr.maqsaf.data.remote.api.ItemApi
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.domain.menu.repository.ItemRepository
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
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