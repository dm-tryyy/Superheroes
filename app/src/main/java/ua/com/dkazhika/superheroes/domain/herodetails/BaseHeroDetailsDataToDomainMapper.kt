package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsDataToDomainMapper

class BaseHeroDetailsDataToDomainMapper : HeroDetailsDataToDomainMapper {
    override fun map(
        id: Int,
        name: String,
        description: String,
        imageUrl: String,
        comicsNames: List<String>
    ): HeroDetailsDomain = HeroDetailsDomain(id, name, description, imageUrl, comicsNames)
}