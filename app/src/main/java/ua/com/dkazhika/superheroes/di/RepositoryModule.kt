package ua.com.dkazhika.superheroes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsRepositoryImpl
import ua.com.dkazhika.superheroes.data.herodetails.remote.HeroDetailsRemoteDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesRepositoryImpl
import ua.com.dkazhika.superheroes.data.heroeslist.remote.HeroesRemoteDataSource
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsRepository
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHeroesRemoteDataSource(
        heroesRemoteDataSource: HeroesRemoteDataSource.Base
    ): HeroesRemoteDataSource

    @Binds
    abstract fun bindHeroesRepository(
        heroesRepository: HeroesRepositoryImpl
    ): HeroesRepository

    @Binds
    abstract fun bindHeroDetailsRemoteDataSource(
        heroDetailsRemoteDataSource: HeroDetailsRemoteDataSource.Base
    ): HeroDetailsRemoteDataSource

    @Binds
    abstract fun bindHeroDetailsRepository(
        heroDetailsRepository: HeroDetailsRepositoryImpl
    ): HeroDetailsRepository
}