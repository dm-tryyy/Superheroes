package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.HeroDomain

sealed class HeroesData : Abstract.Object<HeroDomain, HeroesDataToDomainMapper>() {
    data class Success(private val heroes: List<Hero>) : HeroesData() {
        override fun map(mapper: HeroesDataToDomainMapper) = mapper.map(heroes)
    }
    data class Fail(private val e: Exception) : HeroesData() {
        override fun map(mapper: HeroesDataToDomainMapper) = mapper.map(e)
    }
}