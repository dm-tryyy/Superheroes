package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.presentation.HeroUi

data class HeroDomain(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val imageUrl: String
) : Abstract.Object<HeroUi, HeroDomainToHeroUiMapper> {
    override fun map(mapper: HeroDomainToHeroUiMapper): HeroUi {
        return mapper.map(id, name, imageUrl)
    }
}