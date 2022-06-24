package ua.com.dkazhika.superheroes.presentation.herodetails

import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomainToUiMapper

class BaseHeroDetailsDomainToUiMapper : HeroDetailsDomainToUiMapper {
    override fun map(
        id: Int,
        name: String,
        description: String,
        imageUrl: String,
        comicsNames: List<String>
    ): HeroDetailsUi = HeroDetailsUi.Base(id, name, description, imageUrl, comicsNames)
}