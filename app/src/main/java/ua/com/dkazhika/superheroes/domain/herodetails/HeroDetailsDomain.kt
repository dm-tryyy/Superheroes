package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsUi

data class HeroDetailsDomain(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val imageUrl: String,
    private val comicsNames: List<String>
) : Abstract.Object<HeroDetailsUi, HeroDetailsDomainToUiMapper> {
    override fun map(mapper: HeroDetailsDomainToUiMapper): HeroDetailsUi {
        return mapper.map(id, name, description, imageUrl, comicsNames)
    }
}