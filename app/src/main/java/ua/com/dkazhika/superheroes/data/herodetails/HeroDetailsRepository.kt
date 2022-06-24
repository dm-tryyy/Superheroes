package ua.com.dkazhika.superheroes.data.herodetails

import kotlinx.coroutines.delay
import ua.com.dkazhika.superheroes.data.herodetails.cloud.HeroDetailsCloudDataSource
import ua.com.dkazhika.superheroes.data.herodetails.cloud.HeroDetailsCloudMapper

interface HeroDetailsRepository {

    suspend fun fetchHeroDetails(id: Int): HeroDetailsDataContainer

    class Base(
        private val cloudDataSource: HeroDetailsCloudDataSource,
        private val heroesCloudMapper: HeroDetailsCloudMapper,
    ) : HeroDetailsRepository {
        override suspend fun fetchHeroDetails(id: Int) = try {
            delay(500)
            val charactersContainer = cloudDataSource.fetchHeroDetails(id).data
            val heroDetails = charactersContainer.mapToHeroDetailsData(heroesCloudMapper)
            HeroDetailsDataContainer.Success(heroDetails)
        } catch (e: Exception) {
            HeroDetailsDataContainer.Fail(e)
        }
    }
}