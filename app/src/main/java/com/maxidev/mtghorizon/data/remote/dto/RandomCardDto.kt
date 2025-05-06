package com.maxidev.mtghorizon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomCardDto(
    val id: String? = "",
    @SerialName("oracle_id")
    val oracleId: String? = "",
    val name: String? = "",
    @SerialName("image_uris")
    val imageUris: ImageUris? = ImageUris(),
    @SerialName("oracle_text")
    val oracleText: String? = ""
) {
    @Serializable
    data class ImageUris(
        val png: String? = "",
    )
}