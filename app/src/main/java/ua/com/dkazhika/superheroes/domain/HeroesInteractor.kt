package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.data.HeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.data.HeroesRepository

interface HeroesInteractor {

    suspend fun fetchHeroes(): HeroesDomain

    class Base(
        private val heroesRepository: HeroesRepository,
        private val mapper: HeroesDataToDomainMapper
    ) : HeroesInteractor {
        override suspend fun fetchHeroes() = heroesRepository.fetchHeroes().map(mapper)
    }
}