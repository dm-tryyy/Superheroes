package ua.com.dkazhika.superheroes.data.heroeslist

import retrofit2.HttpException
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.domain.ErrorHandler
import java.io.IOException

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorType {
        return when (throwable) {
            is IOException -> ErrorType.NoConnection
            is HttpException -> ErrorType.ServiceUnavailable
            else -> ErrorType.Unknown
        }
    }
}