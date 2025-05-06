@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxidev.mtghorizon.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.mtghorizon.domain.model.RandomCard
import com.maxidev.mtghorizon.presentation.components.CustomAsyncImage
import com.maxidev.mtghorizon.presentation.components.CustomCenterTopBar
import com.maxidev.mtghorizon.presentation.theme.MTGHorizonTheme
import com.maxidev.mtghorizon.utils.Resource

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenContent(
        state = state,
        navigateToDetail = navigateToDetail
    )
}

@Composable
private fun ScreenContent(
    state: Resource<HomeUiState>,
    navigateToDetail: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CustomCenterTopBar(
                title = { Text("MTG Horizon") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is Resource.Error<*> -> { Text(text = state.message ?: "Error") }
                is Resource.Loading<*> -> { CircularProgressIndicator() }
                is Resource.Success<*> -> {
                    RandomCardItem(
                        randomCard = state.data?.randomCard ?: return@Column,
                        navigateToDetail = navigateToDetail
                    )
                }
            }

            Text(
                text = "TODO: Sets button"
            )
            Text(
                text = "TODO: Recent view section."
            )
        }
    }
}

@Composable
private fun RandomCardItem(
    randomCard: RandomCard,
    navigateToDetail: (String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomAsyncImage(
                imageUrl = randomCard.imageUrl,
                name = randomCard.name,
                onClick = { navigateToDetail(randomCard.id) }
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = randomCard.name,
                    fontSize = 18.sp
                )
                Text(
                    text = randomCard.oracleText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Preview
@Composable
private fun RandomCardItemPreview() {
    MTGHorizonTheme {
        RandomCardItem(
            navigateToDetail = {},
            randomCard = RandomCard(
                imageUrl = "image",
                name = "Lorem impsum",
                oracleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                id = "",
                oracleId = ""
            )
        )
    }
}