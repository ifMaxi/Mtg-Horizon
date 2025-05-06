package com.maxidev.mtghorizon.domain.mappers

import com.maxidev.mtghorizon.data.remote.dto.CardDataDto
import com.maxidev.mtghorizon.domain.model.CardData
import com.maxidev.mtghorizon.domain.model.CardLegalities
import com.maxidev.mtghorizon.domain.model.CardPrices
import com.maxidev.mtghorizon.domain.model.CardPurchaseUris

fun CardDataDto.asDomain() =
    this.let { data ->
        CardData(
            id = data.id.orEmpty(),
            oracleId = data.oracleId.orEmpty(),
            name = data.name.orEmpty(),
            oracleText = data.oracleText.orEmpty(),
            typeLine = data.typeLine.orEmpty(),
            imageUri = data.imageUris?.png.orEmpty(),
            artCrop = data.imageUris?.artCrop.orEmpty(),
            flavorText = data.flavorText.orEmpty(),
            rarity = data.rarity.orEmpty(),
            manaCost = data.manaCost.orEmpty(),
            cmc = data.cmc ?: 0.0,
            power = data.power.orEmpty(),
            toughness = data.toughness.orEmpty(),
            colors = data.colors?.map { it.orEmpty() }.orEmpty(),
            legalities = CardLegalities(
                standard = data.legalities?.standard.orEmpty(),
                future = data.legalities?.future.orEmpty(),
                historic = data.legalities?.historic.orEmpty(),
                timeless = data.legalities?.timeless.orEmpty(),
                gladiator = data.legalities?.gladiator.orEmpty(),
                pioneer = data.legalities?.pioneer.orEmpty(),
                explorer = data.legalities?.explorer.orEmpty(),
                modern = data.legalities?.modern.orEmpty(),
                legacy = data.legalities?.legacy.orEmpty(),
                pauper = data.legalities?.pauper.orEmpty(),
                vintage = data.legalities?.vintage.orEmpty(),
                penny = data.legalities?.penny.orEmpty(),
                commander = data.legalities?.commander.orEmpty(),
                oathbreaker = data.legalities?.oathbreaker.orEmpty(),
                standardbrawl = data.legalities?.standardbrawl.orEmpty(),
                brawl = data.legalities?.brawl.orEmpty(),
                alchemy = data.legalities?.alchemy.orEmpty(),
                paupercommander = data.legalities?.paupercommander.orEmpty(),
                duel = data.legalities?.duel.orEmpty(),
                oldschool = data.legalities?.oldschool.orEmpty(),
                premodern = data.legalities?.premodern.orEmpty(),
                predh = data.legalities?.predh.orEmpty()
            ),
            prices = CardPrices(
                usd = data.prices?.usd.orEmpty(),
                usdFoil = data.prices?.usdFoil.orEmpty(),
                usdEtched = data.prices?.usdEtched.orEmpty(),
                eur = data.prices?.eur.orEmpty(),
                eurFoil = data.prices?.eurFoil.orEmpty(),
                tix = data.prices?.tix.orEmpty()
            ),
            purchaseUris = CardPurchaseUris(
                tcgplayer = data.purchaseUris?.tcgplayer.orEmpty(),
                cardmarket = data.purchaseUris?.cardmarket.orEmpty(),
                cardhoarder = data.purchaseUris?.cardhoarder.orEmpty()
            )
        )
    }