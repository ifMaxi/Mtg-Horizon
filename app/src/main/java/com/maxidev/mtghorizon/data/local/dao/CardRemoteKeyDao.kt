package com.maxidev.mtghorizon.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.mtghorizon.data.local.entity.CardRemoteKeyEntity

@Dao
interface CardRemoteKeyDao {

    @Query("SELECT * FROM card_remote_key WHERE id = :id")
    suspend fun getRemoteKey(id: String): CardRemoteKeyEntity?

    @Upsert
    suspend fun upsertKeys(remoteKeys: List<CardRemoteKeyEntity>)

    @Query("DELETE FROM card_remote_key")
    suspend fun deleteRemoteKeys()
}