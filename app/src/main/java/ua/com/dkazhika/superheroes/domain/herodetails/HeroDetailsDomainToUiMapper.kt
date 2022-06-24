package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsUi

interface HeroDetailsDomainToUiMapper : Abstract.Mapper {
    fun map(
        id: Int,
        name: String,
        description: String,
        imageUrl: String,
        comicsNames: List<String>
    ): HeroDetailsUi
}
