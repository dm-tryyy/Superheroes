package ua.com.dkazhika.superheroes.data.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomain

interface HeroDetailsDataToDomainMapper : Abstract.Mapper {
    fun map(
        id: Int,
        name: String,
        description: String,
        imageUrl: String,
        comicsNames: List<String>
    ): HeroDetailsDomain
}