package ua.com.dkazhika.superheroes.domain.herodetails

interface GetHeroDetailsUseCase {

    suspend fun invoke(id: Int): Result

    class Base(
        private val repository: HeroDetailsRepository
    ) : GetHeroDetailsUseCase {
        override suspend fun invoke(id: Int) = repository.fetchHeroDetails(id)
    }
}