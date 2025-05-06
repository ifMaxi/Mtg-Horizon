package com.maxidev.mtghorizon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SymbologyDto(val `data`: List<Data> = listOf()) {
    @Serializable
    data class Data(
        val symbol: String? = "",
        @SerialName("svg_uri")
        val svgUri: String? = "",
        @SerialName("mana_value")
        val manaValue: Double? = 0.0,
        val cmc: Double? = 0.0
    )
}