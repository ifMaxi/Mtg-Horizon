package com.maxidev.mtghorizon.di

import android.content.Context
import androidx.room.Room
import com.maxidev.mtghorizon.data.local.MtgDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DATA_BASE_NAME = "mtg_database"

    @Provides
    @Singleton
    fun providesRoomDataBase(@ApplicationContext context: Context): MtgDataBase {

        return Room.databaseBuilder(
            context,
            MtgDataBase::class.java,
            DATA_BASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}