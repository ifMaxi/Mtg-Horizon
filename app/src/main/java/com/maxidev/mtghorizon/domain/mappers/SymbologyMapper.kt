package com.maxidev.mtghorizon.domain.mappers

import com.maxidev.mtghorizon.data.remote.dto.SymbologyDto
import com.maxidev.mtghorizon.domain.model.Symbology

fun SymbologyDto.asDomain() =
    this.data.map { sym ->
        Symbology(
            symbol = sym.symbol.orEmpty(),
            svgUri = sym.svgUri.orEmpty(),
            manaValue = sym.manaValue ?: 0.0,
            cmc = sym.cmc ?: 0.0
        )
    }