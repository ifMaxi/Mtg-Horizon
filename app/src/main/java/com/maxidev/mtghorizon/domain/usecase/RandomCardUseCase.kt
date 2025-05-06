package com.maxidev.mtghorizon.domain.usecase

import com.maxidev.mtghorizon.domain.repository.HomeCardRepository
import javax.inject.Inject

class RandomCardUseCase @Inject constructor(
    private val repository: HomeCardRepository
) {
    suspend operator fun invoke() =
        repository.fetchRandomCard()
}