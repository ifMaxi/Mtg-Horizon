package com.maxidev.mtghorizon.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.mtghorizon.data.local.entity.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table WHERE name LIKE '%' || :name || '%'")
    fun pagingCards(name: String): PagingSource<Int, CardEntity>

    @Upsert
    suspend fun upsertCards(entity: List<CardEntity>)

    @Query("DELETE FROM card_table")
    suspend fun clearAll()
}