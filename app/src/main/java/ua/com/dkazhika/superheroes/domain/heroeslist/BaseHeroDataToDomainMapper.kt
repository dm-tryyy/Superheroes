package ua.com.dkazhika.superheroes.domain.heroeslist

import ua.com.dkazhika.superheroes.data.heroeslist.HeroDataToDomainMapper

class BaseHeroDataToDomainMapper : HeroDataToDomainMapper {
    override fun map(id: Int, name: String, imageUrl: String) = HeroDomain(id, name, imageUrl)
}