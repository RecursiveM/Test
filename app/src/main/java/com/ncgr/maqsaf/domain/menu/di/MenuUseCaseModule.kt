package com.ncgr.maqsaf.domain.menu.di

import com.ncgr.maqsaf.domain.menu.repository.ItemRepository
import com.ncgr.maqsaf.domain.menu.repository.MenuRepository
import com.ncgr.maqsaf.domain.menu.usecase.GetAllOrdersUseCase
import com.ncgr.maqsaf.domain.menu.usecase.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MenuUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllOrders(
        menuRepository: MenuRepository
    ): GetAllOrdersUseCase {
        return GetAllOrdersUseCase(menuRepository)
    }
}