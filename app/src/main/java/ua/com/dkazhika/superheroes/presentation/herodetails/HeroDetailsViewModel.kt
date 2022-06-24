package ua.com.dkazhika.superheroes.presentation.herodetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsDomainContainerToUiMapper
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsInteractor

class HeroDetailsViewModel(
    private val interactor: HeroDetailsInteractor,
    private val communication: HeroDetailsCommunication,
    private val mapper: HeroDetailsDomainContainerToUiMapper
) : ViewModel() {

    var isInitialized = false

    fun fetchHeroes(id: Int){
        communication.map(HeroDetailsUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.fetchHeroDetails(id).map(mapper)
            withContext(Dispatchers.Main){
                result.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<HeroDetailsUi>) {
        communication.observe(owner, observer)
    }

    fun initialize(id: Int) {
        isInitialized = true
        fetchHeroes(id)
    }

}
