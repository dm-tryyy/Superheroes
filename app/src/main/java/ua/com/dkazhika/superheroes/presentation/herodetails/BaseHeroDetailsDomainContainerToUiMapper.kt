package ua.com.dkazhika.superheroes.presentation.herodetails

import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomain
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomainContainerToUiMapper
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomainToUiMapper
import ua.com.dkazhika.superheroes.presentation.ResourceProvider

class BaseHeroDetailsDomainContainerToUiMapper(
    private val mapper: HeroDetailsDomainToUiMapper,
    private val resourceProvider: ResourceProvider
) :
    HeroDetailsDomainContainerToUiMapper {
    override fun map(heroDetails: HeroDetailsDomain): HeroDetailsUiContainer {
        val heroDetailsUi = heroDetails.map(mapper)
        return HeroDetailsUiContainer.Success(heroDetailsUi)
    }

    override fun map(errorType: ErrorType): HeroDetailsUiContainer =
        HeroDetailsUiContainer.Fail(errorType, resourceProvider)
}