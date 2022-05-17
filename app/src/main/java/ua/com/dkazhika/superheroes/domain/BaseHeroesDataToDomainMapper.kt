package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.HeroesDataToDomainMapper

class BaseHeroesDataToDomainMapper : HeroesDataToDomainMapper {
    override fun map(heroes: List<Hero>) = HeroDomain.Success(heroes)
    override fun map(e: Exception) = HeroDomain.Fail(e)
}