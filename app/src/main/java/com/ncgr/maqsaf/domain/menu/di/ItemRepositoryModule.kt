package com.ncgr.maqsaf.domain.menu.di

import com.ncgr.maqsaf.data.remote.api.ItemApi
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.domain.menu.repository.ItemRepository
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