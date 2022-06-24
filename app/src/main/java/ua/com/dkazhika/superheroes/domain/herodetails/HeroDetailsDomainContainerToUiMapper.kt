package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsUiContainer

interface HeroDetailsDomainContainerToUiMapper : Abstract.Mapper {

    fun map(heroDetails: HeroDetailsDomain): HeroDetailsUiContainer

    fun map(errorType: ErrorType): HeroDetailsUiContainer

}
