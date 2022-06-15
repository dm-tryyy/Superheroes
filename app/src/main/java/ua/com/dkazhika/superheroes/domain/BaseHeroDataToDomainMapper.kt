package ua.com.dkazhika.superheroes.domain

import ua.com.dkazhika.superheroes.data.HeroDataToDomainMapper

class BaseHeroDataToDomainMapper : HeroDataToDomainMapper {
    override fun map(id: Int, name: String, description: String, imageUrl: String): HeroDomain {
        return HeroDomain(id ,name, description, imageUrl)
    }
}