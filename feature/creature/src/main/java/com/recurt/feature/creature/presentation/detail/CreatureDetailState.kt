package com.recurt.feature.creature.presentation.detail

import com.recurt.feature.creature.domain.model.Creature

data class CreatureDetailState(
    val creature: Creature? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)