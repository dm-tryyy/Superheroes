package ua.com.dkazhika.superheroes.data.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesDomainContainer

sealed class HeroesDataContainer : Abstract.Object<HeroesDomainContainer, HeroesDataToDomainMapper> {
    data class Success(
        private val heroes: List<HeroData>,
    ) : HeroesDataContainer() {
        override fun map(mapper: HeroesDataToDomainMapper): HeroesDomainContainer {
            return mapper.map(heroes)
        }
    }

    data class Fail(private val e: Exception) : HeroesDataContainer() {
        override fun map(mapper: HeroesDataToDomainMapper) = mapper.map(e)
    }
}