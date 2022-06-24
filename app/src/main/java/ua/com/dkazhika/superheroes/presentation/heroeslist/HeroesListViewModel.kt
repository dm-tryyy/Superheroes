package ua.com.dkazhika.superheroes.presentation.heroeslist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesDomainToUiMapper
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesInteractor
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroUi
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroesCommunication

class HeroesListViewModel(
    private val interactor: HeroesInteractor,
    private val mapper: HeroesDomainToUiMapper,
    private val communication: HeroesCommunication
) : ViewModel() {

    fun fetchHeroes(){
        communication.map(listOf(HeroUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.fetchHeroes().map(mapper)
            withContext(Dispatchers.Main){
                result.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<HeroUi>>) {
        communication.observe(owner, observer)
    }
}