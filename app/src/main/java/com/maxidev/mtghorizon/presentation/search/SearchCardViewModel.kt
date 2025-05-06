package com.maxidev.mtghorizon.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.mtghorizon.domain.usecase.CardSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchCardViewModel @Inject constructor(
    private val searchCardUseCase: CardSearchUseCase
): ViewModel() {

    private val _searchState = MutableStateFlow(SearchUiState())
    val searchState = _searchState.asStateFlow()

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun onQueryChange(newValue: String) { searchQuery.value = newValue }

    /**
     * Perform a search with the query.
     * The results are paginated and stored in a temporary database.
     */
    fun searchCards(query: String) {
        _searchState.update {
            it.copy(
                search = searchCardUseCase.invoke(query)
                    .cachedIn(viewModelScope)
            )
        }
    }
}