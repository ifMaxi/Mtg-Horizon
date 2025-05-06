package com.maxidev.mtghorizon.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo val oracleId: String,
    @ColumnInfo val name: String,
    @ColumnInfo val imageUrl: String
)