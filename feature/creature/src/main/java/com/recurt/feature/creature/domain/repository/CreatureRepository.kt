package com.recurt.feature.creature.domain.repository

import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import kotlinx.coroutines.flow.Flow

interface CreatureRepository {
    fun getCreatureList(page: Int, pageSize: Int): Flow<Resource<List<CreatureListItem>>>
    fun getCreatureDetail(id: String): Flow<Resource<Creature>>
}