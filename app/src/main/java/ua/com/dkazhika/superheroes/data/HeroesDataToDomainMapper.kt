package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.HeroDomain

interface HeroesDataToDomainMapper : Abstract.Mapper {

    fun map(heroes: List<Hero>) : HeroDomain
    fun map(e: Exception) : HeroDomain

}