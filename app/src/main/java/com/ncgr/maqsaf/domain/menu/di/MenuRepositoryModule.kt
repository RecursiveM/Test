package com.ncgr.maqsaf.domain.menu.di

import com.ncgr.maqsaf.data.remote.api.ItemApi
import com.ncgr.maqsaf.data.remote.api.MenuApi
import com.ncgr.maqsaf.data.repository.ItemRepositoryImp
import com.ncgr.maqsaf.data.repository.MenuRepositoryImp
import com.ncgr.maqsaf.domain.menu.repository.ItemRepository
import com.ncgr.maqsaf.domain.menu.repository.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MenuRepositoryModule {

    @Singleton
    @Provides
    fun provideItemRepository(
        menuApi: MenuApi
    ): MenuRepository {
        return MenuRepositoryImp(menuApi)
    }
}