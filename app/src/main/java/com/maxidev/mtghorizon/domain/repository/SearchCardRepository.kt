package com.maxidev.mtghorizon.domain.repository

import androidx.paging.PagingData
import com.maxidev.mtghorizon.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface SearchCardRepository {

    fun fetchSearchCards(query: String): Flow<PagingData<Card>>
}