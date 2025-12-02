package com.recurt.feature.creature.data

import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.data.mapper.toCreature
import com.recurt.feature.creature.data.mapper.toCreatureListItem
import com.recurt.feature.creature.data.remote.api.PokemonApi
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.repository.CreatureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi
) : CreatureRepository {

    override fun getCreatureList(page: Int, pageSize: Int): Flow<Resource<List<CreatureListItem>>> = flow {
        emit(Resource.Loading())

        try {
            val response = pokemonApi.getPokemonList(
                limit = pageSize,
                offset = page * pageSize
            )
            val creatures = response.results.map { it.toCreatureListItem() }
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
            val response = pokemonApi.getPokemonDetail(id)
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