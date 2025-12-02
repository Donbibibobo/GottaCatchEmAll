package com.recurt.feature.creature.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponseDto(
    @Json(name = "count") val count: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "previous") val previous: String?,
    @Json(name = "results") val results: List<PokemonListItemDto>
)

@JsonClass(generateAdapter = true)
data class PokemonListItemDto(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class PokemonDetailDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "sprites") val sprites: PokemonSpritesDto,
    @Json(name = "types") val types: List<PokemonTypeSlotDto>,
    @Json(name = "stats") val stats: List<PokemonStatDto>,
    @Json(name = "abilities") val abilities: List<PokemonAbilitySlotDto>?
)

@JsonClass(generateAdapter = true)
data class PokemonSpritesDto(
    @Json(name = "front_default") val frontDefault: String?,
    @Json(name = "other") val other: PokemonOtherSpritesDto?
)

@JsonClass(generateAdapter = true)
data class PokemonOtherSpritesDto(
    @Json(name = "official-artwork") val officialArtwork: PokemonOfficialArtworkDto?
)

@JsonClass(generateAdapter = true)
data class PokemonOfficialArtworkDto(
    @Json(name = "front_default") val frontDefault: String?
)

@JsonClass(generateAdapter = true)
data class PokemonTypeSlotDto(
    @Json(name = "slot") val slot: Int,
    @Json(name = "type") val type: PokemonTypeDto
)

@JsonClass(generateAdapter = true)
data class PokemonTypeDto(
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class PokemonStatDto(
    @Json(name = "base_stat") val baseStat: Int,
    @Json(name = "stat") val stat: PokemonStatInfoDto
)

@JsonClass(generateAdapter = true)
data class PokemonStatInfoDto(
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class PokemonAbilitySlotDto(
    @Json(name = "ability") val ability: PokemonAbilityDto
)

@JsonClass(generateAdapter = true)
data class PokemonAbilityDto(
    @Json(name = "name") val name: String
)