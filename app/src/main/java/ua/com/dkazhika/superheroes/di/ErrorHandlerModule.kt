package ua.com.dkazhika.superheroes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.dkazhika.superheroes.data.heroeslist.ErrorHandlerImpl
import ua.com.dkazhika.superheroes.domain.ErrorHandler

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    abstract fun bindErrorHandler(
        errorHandler: ErrorHandlerImpl
    ) : ErrorHandler
}