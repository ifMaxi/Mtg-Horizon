package com.maxidev.mtghorizon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxidev.mtghorizon.data.local.dao.CardDao
import com.maxidev.mtghorizon.data.local.dao.CardRemoteKeyDao
import com.maxidev.mtghorizon.data.local.entity.CardEntity
import com.maxidev.mtghorizon.data.local.entity.CardRemoteKeyEntity

@Database(
    entities = [
        CardEntity::class,
        CardRemoteKeyEntity::class
               ],
    version = 1,
    exportSchema = false
)
abstract class MtgDataBase: RoomDatabase() {

    abstract fun cardDao(): CardDao
    abstract fun cardRemoteKeyDao(): CardRemoteKeyDao
}