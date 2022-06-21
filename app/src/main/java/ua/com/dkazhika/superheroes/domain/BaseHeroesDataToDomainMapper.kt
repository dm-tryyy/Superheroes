package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.data.HeroData
import ua.com.dkazhika.superheroes.data.HeroDataToDomainMapper
import ua.com.dkazhika.superheroes.data.HeroesDataToDomainMapper

class BaseHeroesDataToDomainMapper(private val mapper: HeroDataToDomainMapper) : HeroesDataToDomainMapper {
    override fun map(heroes: List<HeroData>): HeroesDomain {
        val heroesDomain = heroes.map {
            it.map(mapper)
        }
        return HeroesDomain.Success(heroesDomain)
    }
    override fun map(e: Exception) = HeroesDomain.Fail(e)
}