package com.ncgr.maqsaf.domain.order.repository

import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems() : Flow<Resource<List<Item>>>
}