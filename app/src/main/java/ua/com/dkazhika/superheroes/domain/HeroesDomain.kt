package ua.com.dkazhika.superheroes.domain

import retrofit2.HttpException
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.HeroData
import ua.com.dkazhika.superheroes.data.HeroDataToDomainMapper
import ua.com.dkazhika.superheroes.presentation.HeroesUi
import java.lang.Exception
import java.net.UnknownHostException

sealed class HeroesDomain : Abstract.Object<HeroesUi, HeroesDomainToUiMapper> {
    class Success(
        private val heroes: List<HeroDomain>
    ) : HeroesDomain() {
        override fun map(mapper: HeroesDomainToUiMapper) : HeroesUi {
            return mapper.map(heroes)
        }
    }

    class Fail(private val exception: Exception) : HeroesDomain() {
        override fun map(mapper: HeroesDomainToUiMapper) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}