package com.maxidev.mtghorizon.domain.model

data class CardData(
    val id: String,
    val oracleId: String,
    val name: String,
    val oracleText: String,
    val typeLine: String,
    val imageUri: String,
    val artCrop: String,
    val flavorText: String,
    val rarity: String,
    val manaCost: String,
    val cmc: Double,
    val power: String,
    val toughness: String,
    val colors: List<String>,
    val legalities: CardLegalities,
    val prices: CardPrices,
    val purchaseUris: CardPurchaseUris
)

data class CardLegalities(
    val standard: String,
    val future: String,
    val historic: String,
    val timeless: String,
    val gladiator: String,
    val pioneer: String,
    val explorer: String,
    val modern: String,
    val legacy: String,
    val pauper: String,
    val vintage: String,
    val penny: String,
    val commander: String,
    val oathbreaker: String,
    val standardbrawl: String,
    val brawl: String,
    val alchemy: String,
    val paupercommander: String,
    val duel: String,
    val oldschool: String,
    val premodern: String,
    val predh: String
)

data class CardPrices(
    val usd: String,
    val usdFoil: String,
    val usdEtched: String,
    val eur: String,
    val eurFoil: String,
    val tix: String
)

data class CardPurchaseUris(
    val tcgplayer: String,
    val cardmarket: String,
    val cardhoarder: String
)