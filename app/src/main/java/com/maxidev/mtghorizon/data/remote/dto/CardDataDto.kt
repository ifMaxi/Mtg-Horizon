package com.maxidev.mtghorizon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardDataDto(
    val id: String? = "",
    @SerialName("oracle_id")
    val oracleId: String? = "",
    val name: String? = "",
    @SerialName("type_line")
    val typeLine: String? = "",
    @SerialName("image_uris")
    val imageUris: ImageUris? = ImageUris(),
    @SerialName("mana_cost")
    val manaCost: String? = "",
    val cmc: Double? = 0.0,
    @SerialName("oracle_text")
    val oracleText: String? = "",
    val colors: List<String?>? = listOf(),
    val legalities: Legalities? = Legalities(),
    val rarity: String? = "",
    @SerialName("flavor_text")
    val flavorText: String? = "",
    val prices: Prices? = Prices(),
    @SerialName("purchase_uris")
    val purchaseUris: PurchaseUris? = PurchaseUris(),
    val power: String? = "",
    val toughness: String? = ""
) {
    @Serializable
    data class ImageUris(
        val png: String? = "",
        @SerialName("art_crop")
        val artCrop: String? = ""
    )

    @Serializable
    data class Legalities(
        val standard: String? = "",
        val future: String? = "",
        val historic: String? = "",
        val timeless: String? = "",
        val gladiator: String? = "",
        val pioneer: String? = "",
        val explorer: String? = "",
        val modern: String? = "",
        val legacy: String? = "",
        val pauper: String? = "",
        val vintage: String? = "",
        val penny: String? = "",
        val commander: String? = "",
        val oathbreaker: String? = "",
        val standardbrawl: String? = "",
        val brawl: String? = "",
        val alchemy: String? = "",
        val paupercommander: String? = "",
        val duel: String? = "",
        val oldschool: String? = "",
        val premodern: String? = "",
        val predh: String? = ""
    )

    @Serializable
    data class Prices(
        val usd: String? = "",
        @SerialName("usd_foil")
        val usdFoil: String? = "",
        @SerialName("usd_etched")
        val usdEtched: String? = "",
        val eur: String? = "",
        @SerialName("eur_foil")
        val eurFoil: String? = "",
        val tix: String? = ""
    )

    @Serializable
    data class PurchaseUris(
        val tcgplayer: String? = "",
        val cardmarket: String? = "",
        val cardhoarder: String? = ""
    )
}