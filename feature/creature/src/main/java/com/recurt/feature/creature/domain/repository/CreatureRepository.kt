package com.recurt.feature.creature.domain.repository

import androidx.paging.PagingData
import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import kotlinx.coroutines.flow.Flow

interface CreatureRepository {
    fun getCreatureListPaging(): Flow<PagingData<CreatureListItem>>

    fun getCreatureDetail(id: String): Flow<Resource<Creature>>
}