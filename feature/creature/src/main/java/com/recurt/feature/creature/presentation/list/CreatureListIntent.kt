package com.recurt.feature.creature.presentation.list

sealed class CreatureListIntent {
    object LoadCreatures : CreatureListIntent()
    object LoadMore : CreatureListIntent()
    object Refresh : CreatureListIntent()
    data class SelectCreature(val id: String) : CreatureListIntent()
}