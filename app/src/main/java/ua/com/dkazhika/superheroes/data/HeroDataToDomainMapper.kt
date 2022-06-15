package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.HeroDomain

interface HeroDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String, description: String, imageUrl: String) : HeroDomain
}