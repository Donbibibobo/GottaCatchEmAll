package com.recurt.feature.creature.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DigimonListResponseDto(
    @Json(name = "content") val content: List<DigimonListItemDto>,
    @Json(name = "pageable") val pageable: PageableDto?
)

@JsonClass(generateAdapter = true)
data class DigimonListItemDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "href") val href: String,
    @Json(name = "image") val image: String?
)

@JsonClass(generateAdapter = true)
data class PageableDto(
    @Json(name = "currentPage") val currentPage: Int,
    @Json(name = "elementsOnPage") val elementsOnPage: Int,
    @Json(name = "totalElements") val totalElements: Int,
    @Json(name = "totalPages") val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class DigimonDetailDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "images") val images: List<DigimonImageDto>?,
    @Json(name = "types") val types: List<DigimonTypeDto>?,
    @Json(name = "fields") val fields: List<DigimonFieldDto>?,
    @Json(name = "descriptions") val descriptions: List<DigimonDescriptionDto>?
)

@JsonClass(generateAdapter = true)
data class DigimonImageDto(
    @Json(name = "href") val href: String
)

@JsonClass(generateAdapter = true)
data class DigimonTypeDto(
    @Json(name = "type") val type: String
)

@JsonClass(generateAdapter = true)
data class DigimonFieldDto(
    @Json(name = "field") val field: String
)

@JsonClass(generateAdapter = true)
data class DigimonDescriptionDto(
    @Json(name = "origin") val origin: String,
    @Json(name = "language") val language: String,
    @Json(name = "description") val description: String
)