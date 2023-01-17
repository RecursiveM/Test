package com.ncgr.maqsaf.domain.order.usecase

import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.domain.order.repository.ItemRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(): Flow<Resource<List<Item>>> {
        return itemRepository.getItems()
    }
}