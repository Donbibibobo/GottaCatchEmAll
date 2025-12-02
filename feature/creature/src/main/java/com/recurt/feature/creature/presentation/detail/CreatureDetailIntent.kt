package com.recurt.feature.creature.presentation.detail

sealed class CreatureDetailIntent {
    data class LoadDetail(val id: String) : CreatureDetailIntent()
    object NavigateBack : CreatureDetailIntent()
}