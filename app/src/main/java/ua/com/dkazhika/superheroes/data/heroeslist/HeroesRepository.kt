package ua.com.dkazhika.superheroes.data.heroeslist

import kotlinx.coroutines.delay
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudMapper
import java.lang.Exception

interface HeroesRepository {

    suspend fun fetchHeroes(): HeroesDataContainer

    class Base(
        private val cloudDataSource: HeroesCloudDataSource,
        private val cacheDataSource: HeroesCacheDataSource,
        private val heroesCloudMapper: HeroesCloudMapper,
        private val heroesCacheMapper: HeroesCacheMapper
    ) : HeroesRepository {
        override suspend fun fetchHeroes() = try {
            val heroesCacheList = cacheDataSource.fetchHeroes()
            if (heroesCacheList.isEmpty()) {
                delay(3000)
                val charactersContainer = cloudDataSource.fetchHeroes().data
                val heroes = charactersContainer.map(heroesCloudMapper)
                cacheDataSource.saveHeroes(heroes)
                HeroesDataContainer.Success(heroes)
            } else {
                HeroesDataContainer.Success(heroesCacheMapper.map(heroesCacheList))
            }
        } catch (e: Exception) {
            HeroesDataContainer.Fail(e)
        }
    }
}