package ua.com.dkazhika.superheroes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.dkazhika.superheroes.domain.herodetails.GetHeroDetailsUseCase
import ua.com.dkazhika.superheroes.domain.heroeslist.GetHeroesListUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetHeroesListUseCase(
        getHeroesListUseCase: GetHeroesListUseCase.Base
    ): GetHeroesListUseCase

    @Binds
    abstract fun bindHeroDetailsUseCase(
        getHeroDetailsUseCase: GetHeroDetailsUseCase.Base
    ): GetHeroDetailsUseCase
}