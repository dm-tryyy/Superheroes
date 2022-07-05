package ua.com.dkazhika.superheroes.domain.herodetails

interface HeroDetailsRepository {

    suspend fun fetchHeroDetails(id: Int): Result

}