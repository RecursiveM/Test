package com.ncgr.maqsaf.domain.menu.repository

import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems() : Flow<Resource<List<Item>>>
}