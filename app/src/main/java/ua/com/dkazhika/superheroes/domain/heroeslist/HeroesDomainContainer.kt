package ua.com.dkazhika.superheroes.domain.heroeslist

import retrofit2.HttpException
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroesUiContainer
import java.lang.Exception
import java.net.UnknownHostException

sealed class HeroesDomainContainer : Abstract.Object<HeroesUiContainer, HeroesDomainToUiMapper> {
    class Success(
        private val heroes: List<HeroDomain>
    ) : HeroesDomainContainer() {
        override fun map(mapper: HeroesDomainToUiMapper) : HeroesUiContainer {
            return mapper.map(heroes)
        }
    }

    class Fail(private val exception: Exception) : HeroesDomainContainer() {
        override fun map(mapper: HeroesDomainToUiMapper) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}