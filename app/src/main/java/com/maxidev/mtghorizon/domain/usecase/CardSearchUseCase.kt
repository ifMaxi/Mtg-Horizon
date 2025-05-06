package com.maxidev.mtghorizon.domain.usecase

import com.maxidev.mtghorizon.domain.repository.SearchCardRepository
import javax.inject.Inject

class CardSearchUseCase @Inject constructor(
    private val repository: SearchCardRepository
) {
    operator fun invoke(query: String) =
        repository.fetchSearchCards(query = query)
}