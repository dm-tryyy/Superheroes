package ua.com.dkazhika.superheroes.data.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomainContainer

interface HeroDetailsDataContainerToDomainMapper : Abstract.Mapper{

    fun map(heroDetails: HeroDetailsData) : HeroDetailsDomainContainer
    fun map(e: Exception) : HeroDetailsDomainContainer

}
