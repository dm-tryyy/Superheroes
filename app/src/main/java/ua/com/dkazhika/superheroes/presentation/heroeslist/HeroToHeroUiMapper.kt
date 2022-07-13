package ua.com.dkazhika.superheroes.presentation.heroeslist

import ua.com.dkazhika.superheroes.domain.heroeslist.Hero
import javax.inject.Inject

interface HeroToHeroUiMapper {
    fun map(hero: Hero) : HeroUi

    class Base @Inject constructor() : HeroToHeroUiMapper {
        override fun map(hero: Hero): HeroUi {
            return HeroUi(hero.id, hero.name, hero.imageUrl)
        }
    }
}