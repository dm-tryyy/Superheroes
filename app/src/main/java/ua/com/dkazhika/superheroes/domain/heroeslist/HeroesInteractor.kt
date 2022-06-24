package ua.com.dkazhika.superheroes.domain.heroeslist

import ua.com.dkazhika.superheroes.data.heroeslist.HeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesRepository

interface HeroesInteractor {

    suspend fun fetchHeroes(): HeroesDomainContainer

    class Base(
        private val heroesRepository: HeroesRepository,
        private val mapper: HeroesDataToDomainMapper
    ) : HeroesInteractor {
        override suspend fun fetchHeroes() = heroesRepository.fetchHeroes().map(mapper)
    }
}