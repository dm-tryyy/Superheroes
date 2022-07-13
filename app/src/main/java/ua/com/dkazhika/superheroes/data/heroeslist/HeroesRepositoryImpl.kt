package ua.com.dkazhika.superheroes.data.heroeslist

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import ua.com.dkazhika.superheroes.data.heroeslist.remote.HeroesRemoteDataSource
import ua.com.dkazhika.superheroes.domain.heroeslist.Hero
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesRepository
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource
) : HeroesRepository {

    override fun getHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSource.getHeroes()
    }

}