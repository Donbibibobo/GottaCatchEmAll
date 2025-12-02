package com.recurt.feature.creature.presentation.list

import com.recurt.feature.creature.domain.model.CreatureListItem

data class CreatureListState(
    val creatureListItem: List<CreatureListItem> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 0,
    val canLoadMore: Boolean = true
)