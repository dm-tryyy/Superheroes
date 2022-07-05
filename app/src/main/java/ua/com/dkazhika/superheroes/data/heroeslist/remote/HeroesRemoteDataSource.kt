package ua.com.dkazhika.superheroes.data.heroeslist.remote

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroesDb
import ua.com.dkazhika.superheroes.data.heroeslist.paging.HeroesRemoteMediator
import ua.com.dkazhika.superheroes.data.HeroesApi
import ua.com.dkazhika.superheroes.domain.heroeslist.Hero
import javax.inject.Inject

interface HeroesRemoteDataSource {

    fun getHeroes(): Flow<PagingData<Hero>>

    class Base @Inject constructor(
        private val api: HeroesApi,
        private val database: HeroesDb,
    ) : HeroesRemoteDataSource {
        @OptIn(ExperimentalPagingApi::class)
        override fun getHeroes(): Flow<PagingData<Hero>> {
            return Pager(
                config = PagingConfig(
                    pageSize = HeroesRemoteMediator.LOAD_LIMIT,
                    enablePlaceholders = false
                ),
                remoteMediator = HeroesRemoteMediator(
                    api,
                    database
                ),
                pagingSourceFactory = {
                    database.heroesDao().getHeroes()
                }
            ).flow.map { pagingData -> pagingData.map { it.toHero() } }
        }
    }
}