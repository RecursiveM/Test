package com.ncgr.maqsaf.domain.order.di

import com.ncgr.maqsaf.data.remote.api.item.ItemApi
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ItemRepositoryModule {

    @Singleton
    @Provides
    fun provideItemRepository(
        itemApi: ItemApi
    ): ItemRepository {
        return ItemRepositoryImp(itemApi)
    }
}