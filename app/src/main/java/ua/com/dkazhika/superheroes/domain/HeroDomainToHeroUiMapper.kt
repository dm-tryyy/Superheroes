package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.presentation.HeroUi

interface HeroDomainToHeroUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String, imageUrl: String) : HeroUi
}