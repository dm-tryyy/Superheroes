package ua.com.dkazhika.superheroes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.domain.HeroesDomainToUiMapper
import ua.com.dkazhika.superheroes.domain.HeroesInteractor

class MainViewModel(
    private val interactor: HeroesInteractor,
    private val mapper: HeroesDomainToUiMapper,
    private val communication: HeroesCommunication
) : ViewModel() { //todo interface

    fun fetchHeroes() = viewModelScope.launch(Dispatchers.IO) {
        val result = interactor.fetchHeroes().map(mapper)
        withContext(Dispatchers.Main){
            result.map(Abstract.Mapper.Empty())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<Hero>>) {
        communication.observeSuccess(owner, observer)
    }
}