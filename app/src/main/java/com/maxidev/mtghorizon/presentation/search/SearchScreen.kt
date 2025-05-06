package com.maxidev.mtghorizon.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.maxidev.mtghorizon.presentation.components.CustomAsyncImage
import com.maxidev.mtghorizon.presentation.components.CustomSearchBar

@Composable
fun SearchScreen(
    viewModel: SearchCardViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val state by viewModel.searchState.collectAsStateWithLifecycle()
    val query by viewModel.searchQuery

    SearchScreenContent(
        state = state,
        query = query,
        onQueryChange = viewModel::onQueryChange,
        onSearch = { viewModel.searchCards(it) },
        navigateToDetail = navigateToDetail,
        onResultSelected = {} // TODO: Replace with advanced search.
    )
}

@Composable
private fun SearchScreenContent(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onResultSelected: (String) -> Unit,
    state: SearchUiState,
    navigateToDetail: (String) -> Unit,
    lazyState: LazyGridState = rememberLazyGridState()
) {
    val searchCards = state.search.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CustomSearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                onResultSelected = onResultSelected
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.consumeWindowInsets(innerPadding),
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(10.dp),
            state = lazyState,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(
                count = searchCards.itemCount
            ) { index ->
                val cards = searchCards[index]

                if (cards != null) {
                    CustomAsyncImage(
                        imageUrl = cards.imageUrl,
                        name = cards.name,
                        onClick = { navigateToDetail(cards.id) }
                    )
                }
            }
        }
    }
}