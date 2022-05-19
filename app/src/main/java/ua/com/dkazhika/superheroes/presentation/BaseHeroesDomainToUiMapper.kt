package ua.com.dkazhika.superheroes.presentation

import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.domain.HeroesDomainToUiMapper

class BaseHeroesDomainToUiMapper(
    private val communication: HeroesCommunication,
    private val resourceProvider: ResourceProvider
) : HeroesDomainToUiMapper {
    override fun map(heroes: List<Hero>) = HeroesUi.Success(heroes, communication)
    override fun map(errorType: ErrorType) = HeroesUi.Fail(errorType, communication, resourceProvider)
}