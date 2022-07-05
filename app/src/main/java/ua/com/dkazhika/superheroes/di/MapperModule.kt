package ua.com.dkazhika.superheroes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsToHeroUiMapper
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroToHeroUiMapper

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindHeroToHeroUiMapper(
        mapper: HeroToHeroUiMapper.Base
    ): HeroToHeroUiMapper

    @Binds
    abstract fun bindHeroDetailsToHeroUiMapper(
        mapper: HeroDetailsToHeroUiMapper.Base
    ): HeroDetailsToHeroUiMapper
}