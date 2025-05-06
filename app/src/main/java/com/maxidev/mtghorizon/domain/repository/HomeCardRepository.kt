package com.maxidev.mtghorizon.domain.repository

import com.maxidev.mtghorizon.domain.model.RandomCard

interface HomeCardRepository {

    suspend fun fetchRandomCard(): RandomCard
}