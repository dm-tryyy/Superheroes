package ua.com.dkazhika.superheroes.domain

interface ErrorHandler {

    fun getError(throwable: Throwable) : ErrorType

}
