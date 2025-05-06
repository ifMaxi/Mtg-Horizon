@file:OptIn(ExperimentalMaterial3Api::class)

package com.maxidev.mtghorizon.presentation.detail

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.mtghorizon.domain.model.CardData
import com.maxidev.mtghorizon.domain.model.CardLegalities
import com.maxidev.mtghorizon.domain.model.CardPrices
import com.maxidev.mtghorizon.domain.model.CardPurchaseUris
import com.maxidev.mtghorizon.presentation.components.CustomAsyncImage
import com.maxidev.mtghorizon.presentation.components.CustomCenterTopBar
import com.maxidev.mtghorizon.presentation.preview_utils.cardDataFullScreenPreview
import com.maxidev.mtghorizon.presentation.preview_utils.cardLegalitiesDataPreview
import com.maxidev.mtghorizon.presentation.preview_utils.cardPricesDataPreview
import com.maxidev.mtghorizon.presentation.preview_utils.cardPurchaseUrisDataPreview
import com.maxidev.mtghorizon.presentation.theme.MTGHorizonTheme
import com.maxidev.mtghorizon.utils.Resource

private const val TAG = "CardDataScreen"

// TODO: Under development.

@Composable
fun CardDataScreen(
    id: String,
    viewModel: CardDataViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(String) {
        Log.d(TAG, "LaunchedEffect executed: $id")
        viewModel.getCardData(id)
    }

    ScreenContent(
        state = state
    )
}

@Composable
private fun ScreenContent(
    state: Resource<CardDataUiState>
) {
    val cardData = state.data
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomCenterTopBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigate back */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = { /* TODO: Menu ? */ }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.consumeWindowInsets(innerPadding)) {
            Log.d(TAG, "Box executed")
            CardDataContent(
                cardData = cardData?.cardData ?: return@Box
            )
        }
    }
}

@Composable
private fun CardDataContent(
    cardData: CardData,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        ArtCropHeader(
            artCrop = cardData.artCrop,
            name = cardData.name,
            typeLine = cardData.typeLine
        )
        DescriptionFlavorBottomHeader(
            oracleText = cardData.oracleText,
            flavorText = cardData.flavorText
        )
        CardInformation(
            rarity = cardData.rarity,
            manaCost = cardData.manaCost,
            cmc = cardData.cmc.toString(),
            power = cardData.power,
            toughness = cardData.toughness
        )
        LegalAndPurchaseTabs(
            legals = cardData.legalities,
            prices = cardData.prices,
            purchaseUri = cardData.purchaseUris
        )
    }
}

/* ---------- Header ---------- */

@Composable
private fun ArtCropHeader(
    artCrop: String,
    name: String,
    typeLine: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
        Text(
            text = typeLine,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
        CustomAsyncImage(
            imageUrl = artCrop,
            name = name,
            contentScale = ContentScale.Crop,
            height = 300.dp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black) // TODO: Remove background color.
        )
    }
}

@Preview
@Composable
private fun ArtCropHeaderPreview() {
    MTGHorizonTheme {
        ArtCropHeader(
            artCrop = "Image",
            name = "Lorem impsum",
            typeLine = "Creature — Cat Beast Spirit"
        )
    }
}

/* ---------- Description ~ Flavor ---------- */

@Composable
private fun DescriptionFlavorBottomHeader(
    oracleText: String,
    flavorText: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "\"$flavorText\"",
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            fontWeight = Light
        )
        // TODO: Replace mana cost with an icon.
        Text(
            text = oracleText,
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
private fun DescpFlavorBottomHeader() {
    MTGHorizonTheme {
        DescriptionFlavorBottomHeader(
            oracleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            flavorText = "Duis aute irure dolor in reprehenderit in voluptate velit"
        )
    }
}

/* ---------- Card information ---------- */

@Composable
private fun CardInformation(
    rarity: String,
    manaCost: String,
    cmc: String,
    power: String,
    toughness: String
) {
    // TODO: Replace mana cost with an icon.
    val info = listOf(
        "Rarity\n$rarity",
        "Mana cost\n$manaCost",
        "CMC\n$cmc",
        "Pow/Toug\n$power/$toughness"
    )

    Row(
        modifier = Modifier
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        info.forEach { param ->
            Text(
                text = param,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun CardInformationPreview() {
    MTGHorizonTheme {
        CardInformation(
            rarity = "Legendary",
            manaCost = "{5}{G}{W}",
            cmc = "7.0",
            power = "5",
            toughness = "2",
        )
    }
}

/* ---------- Tabs for legalities, card price and card purchase uri ---------- */

@Composable
private fun LegalAndPurchaseTabs(
    legals: CardLegalities,
    prices: CardPrices,
    purchaseUri: CardPurchaseUris
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val listTabs = listOf("Legalities", "Prices", "Purchase")

    Column {
        PrimaryTabRow(
            selectedTabIndex = selectedIndex
        ) {
            listTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = { Text(text = title) }
                )
            }
        }

        when (selectedIndex) {
            0 -> { LegalitiesTab(legals = legals) }
            1 -> { CardPriceTab(prices = prices) }
            2 -> { PurchaseCardUrisTab(uris = purchaseUri) }
        }
    }
}

@Preview
@Composable
private fun LegalAndPurchaseTabsPreview() {
    MTGHorizonTheme {
        LegalAndPurchaseTabs(
            legals = cardLegalitiesDataPreview,
            prices = cardPricesDataPreview,
            purchaseUri = cardPurchaseUrisDataPreview
        )
    }
}

/* ---------- Legalities ---------- */

@Composable
private fun LegalitiesTab(legals: CardLegalities) {
    // TODO: Improve legalities tab.
    val formats = listOf(
        "Standard: ${legals.standard}",
        "Future: ${legals.future}",
        "Historic: ${legals.historic}",
        "Timeless: ${legals.timeless}",
        "Gladiator: ${legals.gladiator}",
        "Pioneer: ${legals.pioneer}",
        "Explorer: ${legals.explorer}",
        "Modern: ${legals.modern}",
        "Legacy: ${legals.legacy}",
        "Pauper: ${legals.pauper}",
        "Vintage: ${legals.vintage}",
        "Penny: ${legals.penny}",
        "Commander: ${legals.commander}",
        "Oathbreaker: ${legals.oathbreaker}",
        "Standardbrawl: ${legals.standardbrawl}",
        "Brawl: ${legals.brawl}",
        "Alchemy: ${legals.alchemy}",
        "Paupercommander: ${legals.paupercommander}",
        "Duel: ${legals.duel}",
        "Oldschool: ${legals.oldschool}",
        "Premodern: ${legals.premodern}",
        "Predh: ${legals.predh}"
    )

    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        maxItemsInEachColumn = formats.size / 2,
        maxLines = 2,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        itemHorizontalAlignment = Alignment.Start
    ) {
        formats.forEach { legal ->
            Text(
                text = legal
            )
        }
    }
}

@Preview
@Composable
private fun LegalitiesTabPreview() {
    MTGHorizonTheme {
        LegalitiesTab(legals = cardLegalitiesDataPreview)
    }
}

/* ---------- Price card tab ---------- */

@Composable
private fun CardPriceTab(prices: CardPrices) {

    // TODO: Improve prices tab.
    val pricesList = listOf(
        "USD: ${prices.usd}",
        "USDFoil: ${prices.usdFoil}",
        "USDEtched: ${prices.usdEtched}",
        "EUR: ${prices.eur}",
        "EURFoil: ${prices.eurFoil}",
        "Tix: ${prices.tix}"
    )

    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        maxItemsInEachColumn = pricesList.size / 2,
        maxLines = 2,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        itemHorizontalAlignment = Alignment.Start
    ) {
        pricesList.forEach { type ->
            Text(
                text = type
            )
        }
    }
}

@Preview
@Composable
private fun CardPriceTabPreview() {
    MTGHorizonTheme {
        CardPriceTab(prices = cardPricesDataPreview)
    }
}

/* ---------- Purchase card uris ---------- */

@Composable
private fun PurchaseCardUrisTab(uris: CardPurchaseUris) {

    // TODO: Make intent for links.
    val urisList = listOf(
        "TcgPlayer: ${uris.tcgplayer}",
        "Cardmarket: ${uris.cardmarket}",
        "Cardhoarder: ${uris.cardhoarder}"
    )

    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        maxItemsInEachColumn = urisList.size / 2,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        itemHorizontalAlignment = Alignment.Start
    ) {
        urisList.forEach { type ->
            Text(
                text = type
            )
        }
    }
}

@Preview
@Composable
private fun PurchaseCardUrisTabPreview() {
    MTGHorizonTheme {
        PurchaseCardUrisTab(uris = cardPurchaseUrisDataPreview)
    }
}

/* ---------- Preview full screen ---------- */

@Preview(showSystemUi = true)
@Composable
private fun ScreenContentPreview() {
    MTGHorizonTheme {
        CardDataContent(cardData = cardDataFullScreenPreview)
    }
}