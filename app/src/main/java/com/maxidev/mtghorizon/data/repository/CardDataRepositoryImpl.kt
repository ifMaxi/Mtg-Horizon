package com.maxidev.mtghorizon.data.repository

import com.maxidev.mtghorizon.data.remote.MtgHorizonApiService
import com.maxidev.mtghorizon.domain.mappers.asDomain
import com.maxidev.mtghorizon.domain.model.CardData
import com.maxidev.mtghorizon.domain.model.Symbology
import com.maxidev.mtghorizon.domain.repository.CardDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardDataRepositoryImpl @Inject constructor(
    private val api: MtgHorizonApiService
): CardDataRepository {

    override suspend fun fetchCardData(id: String): CardData =
        api.getCardId(id = id)
            .asDomain()

    override suspend fun fetchSymbology(): List<Symbology> =
        withContext(Dispatchers.IO) {
            api.getSymbology()
                .asDomain()
        }
}