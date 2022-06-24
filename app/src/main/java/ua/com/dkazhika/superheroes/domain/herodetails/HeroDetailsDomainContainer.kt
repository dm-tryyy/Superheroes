package ua.com.dkazhika.superheroes.domain.herodetails

import retrofit2.HttpException
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsUiContainer
import java.net.UnknownHostException

sealed class HeroDetailsDomainContainer :
    Abstract.Object<HeroDetailsUiContainer, HeroDetailsDomainContainerToUiMapper> {
    data class Success(
        private val heroDetails: HeroDetailsDomain,
    ) : HeroDetailsDomainContainer() {
        override fun map(mapper: HeroDetailsDomainContainerToUiMapper): HeroDetailsUiContainer {
            return mapper.map(heroDetails)
        }
    }

    data class Fail(private val e: Exception) : HeroDetailsDomainContainer() {
        override fun map(mapper: HeroDetailsDomainContainerToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}