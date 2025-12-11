package com.recurt.feature.creature.presentation.list

sealed class CreatureListIntent {
    data class SelectCreature(val id: String) : CreatureListIntent()
}