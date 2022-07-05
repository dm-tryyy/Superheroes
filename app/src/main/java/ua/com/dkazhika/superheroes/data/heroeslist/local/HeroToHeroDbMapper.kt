package ua.com.dkazhika.superheroes.data.heroeslist.local

import ua.com.dkazhika.superheroes.domain.heroeslist.Hero

interface HeroToHeroDbMapper {
    fun map(hero: Hero) : HeroDb

    class Base: HeroToHeroDbMapper {
        override fun map(hero: Hero): HeroDb = HeroDb(hero.id, hero.name, hero.imageUrl)
    }
}