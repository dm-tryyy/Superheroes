package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.data.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.net.HeroesCloudDataSource
import ua.com.dkazhika.superheroes.data.net.HeroesCloudMapper
import java.lang.Exception

interface HeroesRepository {

    suspend fun fetchHeroes(): HeroesData

    class Base(
        private val cloudDataSource: HeroesCloudDataSource,
        private val cacheDataSource: HeroesCacheDataSource,
        private val heroesCloudMapper: HeroesCloudMapper,
        private val heroesCacheMapper: HeroesCacheMapper
    ) : HeroesRepository {
        override suspend fun fetchHeroes() = try {
            val heroesCacheList = cacheDataSource.fetchHeroes()
            if (heroesCacheList.isEmpty()) {
                val charactersContainer = cloudDataSource.fetchHeroes().data
                val heroes = charactersContainer.map(heroesCloudMapper)
                cacheDataSource.saveHeroes(heroes)
                HeroesData.Success(heroes)
            } else {
                HeroesData.Success(heroesCacheMapper.map(heroesCacheList))
            }
        } catch (e: Exception) {
            HeroesData.Fail(e)
        }
    }
}