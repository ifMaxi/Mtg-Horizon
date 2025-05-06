package com.maxidev.mtghorizon.domain.model

data class Symbology(
    val symbol: String,
    val svgUri: String,
    val manaValue: Double,
    val cmc: Double
)