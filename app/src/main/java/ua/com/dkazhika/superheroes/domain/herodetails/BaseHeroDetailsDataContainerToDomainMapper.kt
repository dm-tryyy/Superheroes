package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsData
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsDataContainerToDomainMapper
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsDataToDomainMapper

class BaseHeroDetailsDataContainerToDomainMapper(private val mapper: HeroDetailsDataToDomainMapper) :
    HeroDetailsDataContainerToDomainMapper {
    override fun map(heroDetails: HeroDetailsData): HeroDetailsDomainContainer {
        val heroDetailsDomain = heroDetails.map(mapper)
        return HeroDetailsDomainContainer.Success(heroDetailsDomain)
    }

    override fun map(e: Exception) = HeroDetailsDomainContainer.Fail(e)
}