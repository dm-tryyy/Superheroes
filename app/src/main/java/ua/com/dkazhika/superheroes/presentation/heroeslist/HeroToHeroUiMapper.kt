package ua.com.dkazhika.superheroes.presentation.heroeslist

import ua.com.dkazhika.superheroes.domain.heroeslist.Hero

interface HeroToHeroUiMapper {
    fun map(hero: Hero) : HeroUi

    class Base : HeroToHeroUiMapper {
        override fun map(hero: Hero): HeroUi {
            return HeroUi(hero.id, hero.name, hero.imageUrl)
        }
    }
}