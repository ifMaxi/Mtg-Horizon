package com.maxidev.mtghorizon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardsDto(
    @SerialName("next_page")
    val nextPage: String? = "",
    val `data`: List<CardDataDto?>? = listOf()
)

@Serializable
data class CardDto(
    @SerialName("next_page")
    val nextPage: String? = "",
    val `data`: List<RandomCardDto?>? = listOf()
)