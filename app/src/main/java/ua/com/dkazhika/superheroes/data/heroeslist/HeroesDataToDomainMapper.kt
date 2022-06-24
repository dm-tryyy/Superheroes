package ua.com.dkazhika.superheroes.data.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesDomainContainer

interface HeroesDataToDomainMapper : Abstract.Mapper {

    fun map(heroes: List<HeroData>) : HeroesDomainContainer
    fun map(e: Exception) : HeroesDomainContainer

}