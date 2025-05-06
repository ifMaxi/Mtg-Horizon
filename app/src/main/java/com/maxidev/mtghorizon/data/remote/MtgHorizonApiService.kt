package com.maxidev.mtghorizon.data.remote

import com.maxidev.mtghorizon.data.remote.dto.CardDataDto
import com.maxidev.mtghorizon.data.remote.dto.CardDto
import com.maxidev.mtghorizon.data.remote.dto.RandomCardDto
import com.maxidev.mtghorizon.data.remote.dto.SymbologyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MtgHorizonApiService {

    /**
     * Card methods
     */
    @GET(CARDS + SEARCH)
    suspend fun getSearchCards(
        @Query("page") page: Int,
        @Query("q") q: String,
        @Query("order") order: String? = null,
        @Query("unique") unique: String? = null,
        @Query("include_extras") includeExtras: Boolean? = null,
        @Query("include_variations") includeVariations: Boolean? = null
    ): CardDto

    @GET(CARDS + RANDOM)
    suspend fun getRandomCard(@Query("version") version: String? = "png"): RandomCardDto

    @GET(CARDS + ID)
    suspend fun getCardId(
        @Path("id") id: String,
        @Query("version") version: String? = "png"
    ): CardDataDto

    /**
     * Set methods
     */
    @GET(SETS)
    suspend fun getSets()

    @GET(SETS + ID)
    suspend fun getSetId(@Path("id") id: String)

    /**
     * Ruling methods
     */
    @GET(CARDS + ID + RULINGS)
    suspend fun getCardRulings(@Path("id") id: String)

    /**
     * Symbology methods
     */
    @GET(SIMBOLOGY)
    suspend fun getSymbology(): SymbologyDto
}

// Endpoint ID for some calls.
private const val ID = "/{id}"

// Card endpoint.
private const val CARDS = "cards"
private const val SEARCH = "/search"
private const val RANDOM = "/random"

// Sets endpoint.
private const val SETS = "sets"

// Ruling endpoint.
private const val RULINGS = "/rulings"

// Symbology endpoint.
private const val SIMBOLOGY = "symbology"