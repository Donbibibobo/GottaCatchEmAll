package com.recurt.feature.creature.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatureListItem(
    val id: String,
    val name: String,
    val imageUrl: String
) : Parcelable

@Parcelize
data class Creature(
    val id: String,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Float,
    val weight: Float,
    val stats: List<Stat>,
    val abilities: List<String>? = null,
    val description: String? = null
) : Parcelable

@Parcelize
data class Stat(
    val name: String,
    val value: Int,
    val maxValue: Int
) : Parcelable