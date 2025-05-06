package com.maxidev.mtghorizon.di

import com.maxidev.mtghorizon.data.repository.CardDataRepositoryImpl
import com.maxidev.mtghorizon.data.repository.HomeCardRepositoryImpl
import com.maxidev.mtghorizon.data.repository.SearchCardRepositoryImpl
import com.maxidev.mtghorizon.domain.repository.CardDataRepository
import com.maxidev.mtghorizon.domain.repository.HomeCardRepository
import com.maxidev.mtghorizon.domain.repository.SearchCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchCardRepository(impl: SearchCardRepositoryImpl): SearchCardRepository

    @Binds
    abstract fun bindHomeRepository(impl: HomeCardRepositoryImpl): HomeCardRepository

    @Binds
    abstract fun bindCardDataRepository(impl: CardDataRepositoryImpl): CardDataRepository
}