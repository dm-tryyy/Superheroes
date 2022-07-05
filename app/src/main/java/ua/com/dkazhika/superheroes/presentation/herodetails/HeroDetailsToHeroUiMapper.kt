package ua.com.dkazhika.superheroes.presentation.herodetails

import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetails

interface HeroDetailsToHeroUiMapper {
    fun map(
        heroDetails: HeroDetails
    ): HeroDetailsUi

    class Base : HeroDetailsToHeroUiMapper {
        override fun map(
            heroDetails: HeroDetails
        ): HeroDetailsUi = HeroDetailsUi(
            heroDetails.id,
            heroDetails.name,
            heroDetails.description,
            heroDetails.imageUrl,
            heroDetails.comicsNames
        )
    }
}
