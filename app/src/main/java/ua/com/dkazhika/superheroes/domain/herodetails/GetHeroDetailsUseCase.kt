package ua.com.dkazhika.superheroes.domain.herodetails

import javax.inject.Inject

interface GetHeroDetailsUseCase {

    suspend fun invoke(id: Int): Result

    class Base @Inject constructor(
        private val repository: HeroDetailsRepository
    ) : GetHeroDetailsUseCase {
        override suspend fun invoke(id: Int) = repository.fetchHeroDetails(id)
    }
}