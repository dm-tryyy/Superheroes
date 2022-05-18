package ua.com.dkazhika.superheroes.core

import android.app.Application
import ua.com.dkazhika.superheroes.presentation.BaseHeroesDomainToUiMapper
import ua.com.dkazhika.superheroes.presentation.HeroesCommunication
import ua.com.dkazhika.superheroes.presentation.MainViewModel
import ua.com.dkazhika.superheroes.presentation.ResourceProvider

class SuperheroesApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val heroesInteractor = TODO()
        mainViewModel = MainViewModel(
            heroesInteractor,
            BaseHeroesDomainToUiMapper(HeroesCommunication.Base(), ResourceProvider.Base(this))
        )
    }
}