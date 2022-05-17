package ua.com.dkazhika.superheroes.core

import android.app.Application
import ua.com.dkazhika.superheroes.data.HeroesRepository
import ua.com.dkazhika.superheroes.domain.BaseHeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.HeroesInteractor

class SuperheroesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val heroesRepository : HeroesRepository = TODO("merge")
        val heroesInteractor = HeroesInteractor.Base(heroesRepository, BaseHeroesDataToDomainMapper())
    }
}