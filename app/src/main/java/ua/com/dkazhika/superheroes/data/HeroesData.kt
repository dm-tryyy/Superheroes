package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.HeroesDomain

sealed class HeroesData : Abstract.Object<HeroesDomain, HeroesDataToDomainMapper> {
    data class Success(
        private val heroes: List<HeroData>,
    ) : HeroesData() {
        override fun map(mapper: HeroesDataToDomainMapper): HeroesDomain {
            return mapper.map(heroes)
        }
    }

    data class Fail(private val e: Exception) : HeroesData() {
        override fun map(mapper: HeroesDataToDomainMapper) = mapper.map(e)
    }
}