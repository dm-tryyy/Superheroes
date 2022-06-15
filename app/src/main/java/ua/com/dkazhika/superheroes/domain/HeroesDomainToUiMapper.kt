package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.presentation.HeroUi
import ua.com.dkazhika.superheroes.presentation.HeroesUi

interface HeroesDomainToUiMapper : Abstract.Mapper {

    fun map(heroes:List<HeroDomain>) : HeroesUi

    fun map(errorType: ErrorType) : HeroesUi

}