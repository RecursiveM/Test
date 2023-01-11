package com.ncgr.maqsaf.domain.menu.repository

import com.ncgr.maqsaf.data.remote.model.MenuDtoItem
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getAllOrders(): Flow<Resource<List<MenuDtoItem>>>
}