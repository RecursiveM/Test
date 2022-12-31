package com.ncgr.maqsaf.domain.menu.usecase

import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.menu.repository.ItemRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(): Flow<Resource<List<Item>>> {
        return itemRepository.getItems()
    }
}