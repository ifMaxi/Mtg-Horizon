package com.maxidev.mtghorizon.presentation.preview_utils

import com.maxidev.mtghorizon.domain.model.CardData
import com.maxidev.mtghorizon.domain.model.CardLegalities
import com.maxidev.mtghorizon.domain.model.CardPrices
import com.maxidev.mtghorizon.domain.model.CardPurchaseUris

val cardDataFullScreenPreview =
    CardData(
        id = "Id",
        oracleId = "OracleId",
        name = "Lorem impsum",
        oracleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        imageUri = "Image",
        typeLine = "Creature — Cat Beast Spirit",
        artCrop = "Art",
        flavorText = "Duis aute irure dolor in reprehenderit in voluptate velit",
        rarity = "Legendary",
        manaCost = "{5}{G}{W}",
        cmc = 7.0,
        power = "6",
        toughness = "3",
        colors = listOf("G", "W"),
        legalities = CardLegalities(
            standard = "legal",
            future = "not_legal",
            historic = "legal",
            timeless = "legal",
            gladiator = "legal",
            pioneer = "legal",
            explorer = "legal",
            modern = "legal",
            legacy = "legal",
            pauper = "legal",
            vintage = "legal",
            penny = "not_legal",
            commander = "legal",
            oathbreaker = "legal",
            standardbrawl = "legal",
            brawl = "legal",
            alchemy = "legal",
            paupercommander = "legal",
            duel = "legal",
            oldschool = "not_legal",
            premodern = "not_legal",
            predh = "not_legal"
        ),
        prices = CardPrices(
            usd = "3.64",
            usdFoil = "27.25",
            usdEtched = "",
            eur = "10.35",
            eurFoil = "30.50",
            tix = "1.87"
        ),
        purchaseUris = CardPurchaseUris(
            tcgplayer = "Link",
            cardmarket = "Link",
            cardhoarder = "Link"
        )
    )

val cardLegalitiesDataPreview =
    CardLegalities(
        standard = "legal",
        future = "not_legal",
        historic = "legal",
        timeless = "legal",
        gladiator = "legal",
        pioneer = "legal",
        explorer = "legal",
        modern = "legal",
        legacy = "legal",
        pauper = "legal",
        vintage = "legal",
        penny = "not_legal",
        commander = "legal",
        oathbreaker = "legal",
        standardbrawl = "legal",
        brawl = "legal",
        alchemy = "legal",
        paupercommander = "legal",
        duel = "legal",
        oldschool = "not_legal",
        premodern = "not_legal",
        predh = "not_legal"
    )

val cardPricesDataPreview =
    CardPrices(
        usd = "10.4",
        usdFoil = "43.8",
        usdEtched = "",
        eur = "6.2",
        eurFoil = "27.8",
        tix = "1.8"
    )

val cardPurchaseUrisDataPreview =
    CardPurchaseUris(
        tcgplayer = "Link",
        cardmarket = "Link",
        cardhoarder = "Link"
    )