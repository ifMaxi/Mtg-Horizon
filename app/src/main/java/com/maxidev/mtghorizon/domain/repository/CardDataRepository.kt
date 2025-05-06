package com.maxidev.mtghorizon.domain.repository

import com.maxidev.mtghorizon.domain.model.CardData
import com.maxidev.mtghorizon.domain.model.Symbology

interface CardDataRepository {

    suspend fun fetchCardData(id: String): CardData

    suspend fun fetchSymbology(): List<Symbology>
}