package com.recurt.feature.creature.data.mapper

import com.recurt.feature.creature.data.remote.dto.PokemonDetailDto
import com.recurt.feature.creature.data.remote.dto.PokemonListItemDto
import com.recurt.feature.creature.domain.model.Creature
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.model.Stat
import java.util.Locale


fun PokemonListItemDto.toCreatureListItem(): CreatureListItem {
    val id = url.split("/").dropLast(1).last()
    return CreatureListItem(
        id = id,
        name = name.capitalize(Locale.ROOT),
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}

fun PokemonDetailDto.toCreature(): Creature {
    return Creature(
        id = id.toString(),
        name = name.capitalize(Locale.ROOT),
        imageUrl = sprites.other?.officialArtwork?.frontDefault
            ?: sprites.frontDefault
            ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
        types = types.sortedBy { it.slot }.map { it.type.name },
        height = height / 10f, // Convert to meters
        weight = weight / 10f, // Convert to kg
        stats = stats.map {
            Stat(
                name = mapStatName(it.stat.name),
                value = it.baseStat,
                maxValue = 255
            )
        },
        abilities = abilities?.map { it.ability.name.replace("-", " ").capitalize(Locale.ROOT) }
    )
}

private fun mapStatName(statName: String): String {
    return when (statName) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SP.ATK"
        "special-defense" -> "SP.DEF"
        "speed" -> "SPD"
        else -> statName.uppercase()
    }
}