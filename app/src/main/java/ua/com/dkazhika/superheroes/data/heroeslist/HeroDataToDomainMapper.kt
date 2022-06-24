package ua.com.dkazhika.superheroes.data.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroDomain

interface HeroDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String, imageUrl: String) : HeroDomain
}