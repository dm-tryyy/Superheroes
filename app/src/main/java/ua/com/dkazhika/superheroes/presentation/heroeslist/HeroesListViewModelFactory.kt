package ua.com.dkazhika.superheroes.presentation.heroeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.com.dkazhika.superheroes.domain.heroeslist.GetHeroesListUseCase
import java.lang.IllegalArgumentException

class HeroesListViewModelFactory(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val mapper: HeroToHeroUiMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroesListViewModel::class.java)) {
            return HeroesListViewModel(getHeroesListUseCase, mapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}