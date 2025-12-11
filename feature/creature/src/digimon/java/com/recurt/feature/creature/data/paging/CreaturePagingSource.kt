package com.recurt.feature.creature.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.recurt.feature.creature.data.mapper.toCreatureListItem
import com.recurt.feature.creature.data.remote.api.DigimonApi
import com.recurt.feature.creature.domain.model.CreatureListItem

class CreaturePagingSource(
    private val digimonApi: DigimonApi
) : PagingSource<Int, CreatureListItem>() {

    override fun getRefreshKey(state: PagingState<Int, CreatureListItem>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CreatureListItem> {
        return try {
            val page = params.key ?: 0
            val loadSize = params.loadSize

            val response = digimonApi.getDigimonList(
                page = page,
                pageSize = loadSize
            )

            val creatures = response.content.map { it.toCreatureListItem() }

            LoadResult.Page(
                data = creatures,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (creatures.isEmpty() || creatures.size < loadSize) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(mapToDomainException(e))
        }
    }

    private fun mapToDomainException(e: Exception): Throwable {
        return when (e) {
            is java.net.UnknownHostException -> Throwable("No internet connection")
            is java.net.SocketTimeoutException -> Throwable("Connection timeout")
            is retrofit2.HttpException -> Throwable("Server error (${e.code()})")
            else -> Throwable(e.localizedMessage ?: "Unknown error")
        }
    }
}