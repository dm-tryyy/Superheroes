package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsDataContainerToDomainMapper
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsRepository

interface HeroDetailsInteractor {

    suspend fun fetchHeroDetails(id: Int): HeroDetailsDomainContainer

    class Base(
        private val repository: HeroDetailsRepository,
        private val mapper: HeroDetailsDataContainerToDomainMapper
    ) : HeroDetailsInteractor {
        override suspend fun fetchHeroDetails(id: Int) = repository.fetchHeroDetails(id).map(mapper)
    }
}