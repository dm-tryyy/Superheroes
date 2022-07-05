package ua.com.dkazhika.superheroes.domain.herodetails

import ua.com.dkazhika.superheroes.domain.ErrorType

sealed class Result {
    data class Success(
        val heroDetails: HeroDetails
    ) : Result()

    class Fail(val error: ErrorType) : Result()

}