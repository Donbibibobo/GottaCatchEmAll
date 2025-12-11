package com.recurt.feature.creature.domain.usecase

import androidx.paging.PagingData
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.repository.CreatureRepository
import kotlinx.coroutines.flow.Flow

class GetCreatureListUseCase(
    private val repository: CreatureRepository
) {
    operator fun invoke(): Flow<PagingData<CreatureListItem>> {
        return repository.getCreatureListPaging()
    }
}