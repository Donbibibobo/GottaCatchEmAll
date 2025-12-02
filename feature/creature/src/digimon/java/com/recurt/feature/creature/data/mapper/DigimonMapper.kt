package com.recurt.feature.creature.data.mapper

import com.recurt.feature.creature.data.remote.dto.DigimonDetailDto
import com.recurt.feature.creature.data.remote.dto.DigimonListItemDto
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.model.Stat
import kotlin.random.Random

fun DigimonListItemDto.toCreatureListItem(): CreatureListItem {
    return CreatureListItem(
        id = id.toString(),
        name = name,
        imageUrl = image ?: ""
    )
}

fun DigimonDetailDto.toCreature(): Creature {
    return Creature(
        id = id.toString(),
        name = name,
        imageUrl = images?.firstOrNull()?.href ?: "",
        types = types?.map { it.type } ?: emptyList(),
        height = Random.nextFloat() * 10, // Simulated data
        weight = Random.nextFloat() * 100, // Simulated data
        stats = generateDigimonStats(),
        description = descriptions?.find { it.language == "en_us" }?.description,
        abilities = fields?.map { it.field } ?: emptyList()
    )
}

private fun generateDigimonStats(): List<Stat> {
    // Simulated stats for Digimon
    return listOf(
        Stat("HP", Random.nextInt(50, 200), 200),
        Stat("ATK", Random.nextInt(50, 200), 200),
        Stat("DEF", Random.nextInt(50, 200), 200),
        Stat("SPD", Random.nextInt(50, 200), 200),
        Stat("INT", Random.nextInt(50, 200), 200)
    )
}