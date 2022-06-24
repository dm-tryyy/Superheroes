package ua.com.dkazhika.superheroes.presentation.heroeslist

import ua.com.dkazhika.superheroes.domain.heroeslist.HeroDomainToHeroUiMapper

class BaseHeroDomainToHeroUiMapper : HeroDomainToHeroUiMapper {
    override fun map(id: Int, name: String, imageUrl: String): HeroUi {
        return HeroUi.Base(id, name, imageUrl)
    }
}