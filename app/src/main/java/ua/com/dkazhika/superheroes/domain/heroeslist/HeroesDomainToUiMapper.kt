package ua.com.dkazhika.superheroes.domain.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroesUiContainer

interface HeroesDomainToUiMapper : Abstract.Mapper {

    fun map(heroes:List<HeroDomain>) : HeroesUiContainer

    fun map(errorType: ErrorType) : HeroesUiContainer

}