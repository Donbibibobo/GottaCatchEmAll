package com.recurt.feature.creature.domain.usecase

import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.repository.CreatureRepository
import kotlinx.coroutines.flow.Flow

class GetCreatureDetailUseCase(
    private val repository: CreatureRepository
) {
    operator fun invoke(id: String): Flow<Resource<Creature>> {
        return repository.getCreatureDetail(id)
    }
}