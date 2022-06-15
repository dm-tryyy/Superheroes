package ua.com.dkazhika.superheroes.presentation

import ua.com.dkazhika.superheroes.domain.HeroDomainToHeroUiMapper

class BaseHeroDomainToHeroUiMapper : HeroDomainToHeroUiMapper {
    override fun map(id: Int, name: String, imageUrl: String): HeroUi {
        return HeroUi.Base(id, name, imageUrl)
    }
}