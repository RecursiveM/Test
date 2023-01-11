package com.ncgr.maqsaf.domain.menu.usecase

import com.ncgr.maqsaf.data.remote.model.MenuDtoItem
import com.ncgr.maqsaf.domain.menu.repository.MenuRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllOrdersUseCase(
    private val menuRepository: MenuRepository
) {
    operator fun invoke(): Flow<Resource<List<MenuDtoItem>>> =
        menuRepository.getAllOrders()
}