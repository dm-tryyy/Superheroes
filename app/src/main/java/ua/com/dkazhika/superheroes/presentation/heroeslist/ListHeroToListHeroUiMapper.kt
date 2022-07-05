package ua.com.dkazhika.superheroes.presentation.heroeslist

import ua.com.dkazhika.superheroes.domain.heroeslist.Hero

interface ListHeroToListHeroUiMapper {

    fun map(heroes: List<Hero>): List<HeroUi>

    class Base(
        private val mapper: HeroToHeroUiMapper
    ) : ListHeroToListHeroUiMapper {
        override fun map(heroes: List<Hero>) = heroes.map {
            mapper.map(it)
        }
    }
}