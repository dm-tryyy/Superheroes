package ua.com.dkazhika.superheroes.domain

sealed class ErrorType {

    object NoConnection : ErrorType()

    object ServiceUnavailable : ErrorType()

    object Unknown : ErrorType()

}