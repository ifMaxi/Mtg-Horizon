package com.maxidev.mtghorizon.presentation.search

import androidx.paging.PagingData
import com.maxidev.mtghorizon.domain.model.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchUiState(
    val search: Flow<PagingData<Card>> = emptyFlow()
)