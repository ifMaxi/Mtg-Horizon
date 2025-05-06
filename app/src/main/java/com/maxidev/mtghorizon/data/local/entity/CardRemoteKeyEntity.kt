package com.maxidev.mtghorizon.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_remote_key")
data class CardRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo val nextKey: Int?,
    @ColumnInfo val lastUpdate: Long
)