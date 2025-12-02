package com.recurt.feature.creature.domain.usecase

import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.repository.CreatureRepository
import kotlinx.coroutines.flow.Flow

class GetCreatureListUseCase(
    private val repository: CreatureRepository
) {
    operator fun invoke(page: Int = 0, pageSize: Int = 20): Flow<Resource<List<CreatureListItem>>> {
        return repository.getCreatureList(page, pageSize)
    }
}