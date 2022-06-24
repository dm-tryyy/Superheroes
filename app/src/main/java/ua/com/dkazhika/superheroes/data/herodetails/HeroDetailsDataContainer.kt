package ua.com.dkazhika.superheroes.data.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomainContainer

sealed class HeroDetailsDataContainer : Abstract.Object<HeroDetailsDomainContainer, HeroDetailsDataContainerToDomainMapper> {
    data class Success(
        private val heroDetails: HeroDetailsData,
    ) : HeroDetailsDataContainer() {
        override fun map(mapper: HeroDetailsDataContainerToDomainMapper): HeroDetailsDomainContainer {
            return mapper.map(heroDetails)
        }
    }

    data class Fail(private val e: Exception) : HeroDetailsDataContainer() {
        override fun map(mapper: HeroDetailsDataContainerToDomainMapper) = mapper.map(e)
    }
}
