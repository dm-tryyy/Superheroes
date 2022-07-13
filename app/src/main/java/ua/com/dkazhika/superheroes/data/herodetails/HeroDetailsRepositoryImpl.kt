package ua.com.dkazhika.superheroes.data.herodetails

import kotlinx.coroutines.delay
import ua.com.dkazhika.superheroes.data.herodetails.remote.HeroDetailsRemoteDataSource
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsRepository
import ua.com.dkazhika.superheroes.domain.herodetails.Result
import ua.com.dkazhika.superheroes.domain.ErrorHandler
import javax.inject.Inject

class HeroDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: HeroDetailsRemoteDataSource,
    private val errorHandler: ErrorHandler
) : HeroDetailsRepository {
    override suspend fun fetchHeroDetails(id: Int) = try {
        delay(500)
        val charactersContainer = remoteDataSource.getHeroDetails(id).data
        val heroDetails = charactersContainer.toHeroDetails()
        Result.Success(heroDetails)
    } catch (e: Exception) {
        Result.Fail(errorHandler.getError(e))
    }
}