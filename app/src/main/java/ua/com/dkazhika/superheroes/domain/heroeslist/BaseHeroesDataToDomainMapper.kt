package ua.com.dkazhika.superheroes.domain.heroeslist

import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.heroeslist.HeroDataToDomainMapper
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesDataToDomainMapper

class BaseHeroesDataToDomainMapper(private val mapper: HeroDataToDomainMapper) :
    HeroesDataToDomainMapper {
    override fun map(heroes: List<HeroData>): HeroesDomainContainer {
        val heroesDomain = heroes.map {
            it.map(mapper)
        }
        return HeroesDomainContainer.Success(heroesDomain)
    }
    override fun map(e: Exception) = HeroesDomainContainer.Fail(e)
}