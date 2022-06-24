package ua.com.dkazhika.superheroes.presentation.heroeslist

import ua.com.dkazhika.superheroes.domain.*
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroDomain
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroDomainToHeroUiMapper
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesDomainToUiMapper
import ua.com.dkazhika.superheroes.presentation.ResourceProvider

class BaseHeroesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val mapper: HeroDomainToHeroUiMapper
) : HeroesDomainToUiMapper {
    override fun map(heroes: List<HeroDomain>) : HeroesUiContainer.Success {
        val heroesUi = heroes.map {
            it.map(mapper)
        }
        return HeroesUiContainer.Success(heroesUi)
    }
    override fun map(errorType: ErrorType) = HeroesUiContainer.Fail(errorType, resourceProvider)
}