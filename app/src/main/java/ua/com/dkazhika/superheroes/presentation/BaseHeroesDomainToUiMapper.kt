package ua.com.dkazhika.superheroes.presentation

import ua.com.dkazhika.superheroes.domain.*

class BaseHeroesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: HeroDomainToHeroUiMapper
) : HeroesDomainToUiMapper {
    override fun map(heroes: List<HeroDomain>) : HeroesUi.Success {
        val heroesUi = heroes.map {
            it.map(mapper)
        }
        return HeroesUi.Success(heroesUi)
    }
    override fun map(errorType: ErrorType) = HeroesUi.Fail(errorType, resourceProvider)
}