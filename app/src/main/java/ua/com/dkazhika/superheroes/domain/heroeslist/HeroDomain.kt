package ua.com.dkazhika.superheroes.domain.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroUi

data class HeroDomain(
    private val id: Int,
    private val name: String,
    private val imageUrl: String
) : Abstract.Object<HeroUi, HeroDomainToHeroUiMapper> {
    override fun map(mapper: HeroDomainToHeroUiMapper): HeroUi {
        return mapper.map(id, name, imageUrl)
    }
}