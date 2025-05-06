package com.maxidev.mtghorizon.domain.mappers

import com.maxidev.mtghorizon.data.local.entity.CardEntity
import com.maxidev.mtghorizon.data.remote.dto.CardDto
import com.maxidev.mtghorizon.data.remote.dto.RandomCardDto
import com.maxidev.mtghorizon.domain.model.Card
import com.maxidev.mtghorizon.domain.model.RandomCard

fun CardDto.asEntity() =
    this.data?.map { data ->
        CardEntity(
            id = data?.id.orEmpty(),
            oracleId = data?.oracleId.orEmpty(),
            name = data?.name.orEmpty(),
            imageUrl = data?.imageUris?.png.orEmpty()
        )
    }

fun CardEntity.asDomain() =
    Card(
        id = id,
        oracleId = oracleId,
        name = name,
        imageUrl = imageUrl
    )

fun RandomCardDto.asDomain() =
    this.let { data ->
        RandomCard(
            id = data.id.orEmpty(),
            oracleId = data.oracleId.orEmpty(),
            name = data.name.orEmpty(),
            oracleText = data.oracleText.orEmpty(),
            imageUrl = data.imageUris?.png.orEmpty()
        )
    }