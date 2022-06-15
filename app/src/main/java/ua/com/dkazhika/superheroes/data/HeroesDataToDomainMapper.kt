package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.HeroDomain
import ua.com.dkazhika.superheroes.domain.HeroesDomain

interface HeroesDataToDomainMapper : Abstract.Mapper {

    fun map(heroes: List<HeroData>) : HeroesDomain
    fun map(e: Exception) : HeroesDomain

}