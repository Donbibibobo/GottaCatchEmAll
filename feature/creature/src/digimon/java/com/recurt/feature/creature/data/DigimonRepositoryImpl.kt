package com.recurt.feature.creature.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.recurt.core.common.util.Constants
import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.data.mapper.toCreature
import com.recurt.feature.creature.data.paging.CreaturePagingSource
import com.recurt.feature.creature.data.remote.api.DigimonApi
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.repository.CreatureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DigimonRepositoryImpl(
    private val digimonApi: DigimonApi
) : CreatureRepository {

    override fun getCreatureListPaging(): Flow<PagingData<CreatureListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                initialLoadSize = Constants.PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { CreaturePagingSource(digimonApi) }
        ).flow
    }

    override fun getCreatureDetail(id: String): Flow<Resource<Creature>> = flow {
        emit(Resource.Loading())

        try {
            val response = digimonApi.getDigimonDetail(id)
            val creature = response.toCreature()
            emit(Resource.Success(creature))
        } catch (e: Exception) {
            emit(Resource.Error(
                message = when (e) {
                    is java.net.UnknownHostException -> "No internet connection"
                    is java.net.SocketTimeoutException -> "Connection timeout"
                    is retrofit2.HttpException -> "Server error (${e.code()})"
                    else -> e.localizedMessage ?: "Unknown error"
                }
            ))
        }
    }.flowOn(Dispatchers.IO)
}