package com.maxidev.mtghorizon.domain.model

data class RandomCard(
    val id: String,
    val oracleId: String,
    val name: String,
    val oracleText: String,
    val imageUrl: String
)