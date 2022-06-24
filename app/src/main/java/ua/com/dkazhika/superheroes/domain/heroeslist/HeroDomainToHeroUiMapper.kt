package ua.com.dkazhika.superheroes.domain.heroeslist

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroUi

interface HeroDomainToHeroUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String, imageUrl: String) : HeroUi
}