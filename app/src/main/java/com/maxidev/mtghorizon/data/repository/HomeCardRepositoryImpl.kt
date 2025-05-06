package com.maxidev.mtghorizon.data.repository

import com.maxidev.mtghorizon.data.remote.MtgHorizonApiService
import com.maxidev.mtghorizon.domain.mappers.asDomain
import com.maxidev.mtghorizon.domain.model.RandomCard
import com.maxidev.mtghorizon.domain.repository.HomeCardRepository
import javax.inject.Inject

class HomeCardRepositoryImpl @Inject constructor(
    private val api: MtgHorizonApiService
): HomeCardRepository {

    override suspend fun fetchRandomCard(): RandomCard =
        api.getRandomCard()
            .asDomain()
}