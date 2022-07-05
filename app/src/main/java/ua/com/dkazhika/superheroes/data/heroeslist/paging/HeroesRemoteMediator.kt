package ua.com.dkazhika.superheroes.data.heroeslist.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroDb
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroesDb
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroesRemoteKeys
import ua.com.dkazhika.superheroes.data.HeroesApi
import ua.com.dkazhika.superheroes.data.servermodels.CharacterDataWrapper

@OptIn(ExperimentalPagingApi::class)
class HeroesRemoteMediator(
    private val service: HeroesApi,
    private val heroesDb: HeroesDb
) : RemoteMediator<Int, HeroDb>() {

    private val heroesDao = heroesDb.heroesDao()
    private val heroesRemoteKeys = heroesDb.heroesRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, HeroDb>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextKey
                }
            }

            val offset = currentPage * LOAD_LIMIT

            val type = object : TypeToken<CharacterDataWrapper>() {}.type
            val response = service.getHeroes(offset = offset)
            val dataWrapper : CharacterDataWrapper = Gson().fromJson(response.string(), type)
            val heroes = dataWrapper.data.results.map { it.toHeroDb() }
            val endOfPaginationReached = heroes.isEmpty()

            heroesDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    heroesDao.deleteAllHeroes()
                    heroesRemoteKeys.deleteAllRemoteKeys()
                }

                val prevPage = if (currentPage == 0) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1

                val keys = heroes.map { hero ->
                    HeroesRemoteKeys(
                        id = hero.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                heroesRemoteKeys.addAllRemoteKeys(remoteKeys = keys)
                heroesDao.saveHeroes(heroes = heroes)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, HeroDb>
    ): HeroesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroesRemoteKeys.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HeroDb>
    ): HeroesRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                heroesRemoteKeys.getRemoteKeys(id = hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, HeroDb>
    ): HeroesRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroesRemoteKeys.getRemoteKeys(id = hero.id)
            }
    }
    companion object {
        const val LOAD_LIMIT = 3
    }
}