package com.maxidev.mtghorizon.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.maxidev.mtghorizon.data.local.MtgDataBase
import com.maxidev.mtghorizon.data.local.entity.CardEntity
import com.maxidev.mtghorizon.data.local.entity.CardRemoteKeyEntity
import com.maxidev.mtghorizon.data.remote.MtgHorizonApiService
import com.maxidev.mtghorizon.domain.mappers.asEntity
import com.maxidev.mtghorizon.utils.INITIAL_PAGE
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CardMediator(
    private val api: MtgHorizonApiService,
    private val dataBase: MtgDataBase,
    private val query: String
): RemoteMediator<Int, CardEntity>() {

    private val cardDao = dataBase.cardDao()
    private val cardRemoteKeysDao = dataBase.cardRemoteKeyDao()
    private val time = System.currentTimeMillis()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CardEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val position = getRemoteKeyClosestToCurrentPosition(state)
                position?.nextKey?.plus(1) ?: INITIAL_PAGE
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(true)
            }
            LoadType.APPEND -> {
                val lastItem = getRemoteKeyForLastItem(state)
                val nextPage = lastItem?.nextKey
                    ?: return MediatorResult.Success(true)

                nextPage
            }
        }

        return try {

            val perPage = state.config.pageSize
            val response = api.getSearchCards(
                page = page,
                q = query,
                order = "name",
                unique = "cards",
                includeExtras = false,
                includeVariations = false
            )
            val endOfPagination = response.data?.size!! < perPage
            val nextKey = if (endOfPagination) null else page + 1

            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    cardDao.clearAll()
                    cardRemoteKeysDao.deleteRemoteKeys()
                }

                val remoteKey = response.data.map { key ->
                    CardRemoteKeyEntity(
                        id = key?.id.orEmpty(),
                        nextKey = nextKey,
                        lastUpdate = time
                    )
                }
                val responseAsEntity = response.asEntity()

                cardDao.upsertCards(responseAsEntity.orEmpty())
                cardRemoteKeysDao.upsertKeys(remoteKey)
            }

            MediatorResult.Success(true)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CardEntity>
    ): CardRemoteKeyEntity? {

        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                cardRemoteKeysDao.getRemoteKey(id)
            }
        }
    }

//    private suspend fun getRemoteKeyForFirstItem(
//        state: PagingState<Int, CardEntity>
//    ): CardRemoteKeyEntity? {
//
//        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
//            ?.let {
//                cardRemoteKeysDao.getRemoteKey(it.id)
//            }
//    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CardEntity>
    ): CardRemoteKeyEntity? {

        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                cardRemoteKeysDao.getRemoteKey(it.id)
            }
    }
}

// TODO: Fix load more pages.