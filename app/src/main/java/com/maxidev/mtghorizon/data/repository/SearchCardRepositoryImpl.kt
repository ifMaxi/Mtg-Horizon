package com.maxidev.mtghorizon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.mtghorizon.data.local.MtgDataBase
import com.maxidev.mtghorizon.data.local.entity.CardEntity
import com.maxidev.mtghorizon.data.remote.MtgHorizonApiService
import com.maxidev.mtghorizon.data.repository.paging.CardMediator
import com.maxidev.mtghorizon.domain.mappers.asDomain
import com.maxidev.mtghorizon.domain.model.Card
import com.maxidev.mtghorizon.domain.repository.SearchCardRepository
import com.maxidev.mtghorizon.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SearchCardRepositoryImpl @Inject constructor(
    private val api: MtgHorizonApiService,
    private val database: MtgDataBase
): SearchCardRepository {

    override fun fetchSearchCards(query: String): Flow<PagingData<Card>> {
        val pagingSourceFactory = { database.cardDao().pagingCards(query) }
        val remoteMediator = CardMediator(
            api = api,
            dataBase = database,
            query = query
        )
        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true
        )

        return Pager(
            config = pagingConfig,
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData -> pagingData.map(CardEntity::asDomain) }
            //.flowOn(Dispatchers.Default)
    }
}