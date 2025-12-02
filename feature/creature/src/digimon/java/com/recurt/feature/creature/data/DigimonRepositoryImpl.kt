package com.recurt.feature.creature.data

import android.util.Log
import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.data.mapper.toCreature
import com.recurt.feature.creature.data.mapper.toCreatureListItem
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

    override fun getCreatureList(page: Int, pageSize: Int): Flow<Resource<List<CreatureListItem>>> = flow {
        emit(Resource.Loading())

        try {
            val response = digimonApi.getDigimonList(
                page = page,
                pageSize = pageSize
            )
            val creatures = response.content.map { it.toCreatureListItem() }
            emit(Resource.Success(creatures))
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

    override fun getCreatureDetail(id: String): Flow<Resource<Creature>> = flow {
        emit(Resource.Loading())

        try {
            val response = digimonApi.getDigimonDetail(id)
            val creature = response.toCreature()
            Log.i("shi@", "response: $response")
            Log.i("shi@", "creature: $creature")
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