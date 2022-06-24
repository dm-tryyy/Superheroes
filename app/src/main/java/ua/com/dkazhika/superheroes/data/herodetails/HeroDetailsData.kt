package ua.com.dkazhika.superheroes.data.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomain

data class HeroDetailsData(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val imageUrl: String,
    private val comicsNames: List<String>
) : Abstract.Object<HeroDetailsDomain, HeroDetailsDataToDomainMapper> {
    override fun map(mapper: HeroDetailsDataToDomainMapper): HeroDetailsDomain {
        return mapper.map(id, name, description, imageUrl, comicsNames)
    }
}