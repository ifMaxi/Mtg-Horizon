package com.maxidev.mtghorizon.domain.usecase

import com.maxidev.mtghorizon.domain.repository.CardDataRepository
import javax.inject.Inject

class CardDataUseCase @Inject constructor(
    private val repository: CardDataRepository
) {
    suspend operator fun invoke(id: String) =
        repository.fetchCardData(id)
}