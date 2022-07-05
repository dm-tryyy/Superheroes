package ua.com.dkazhika.superheroes.presentation.heroeslist

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.com.dkazhika.superheroes.domain.heroeslist.GetHeroesListUseCase
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val mapper: HeroToHeroUiMapper
) : ViewModel() {

    fun getHeroes(): Flow<PagingData<HeroUi>> {
        return getHeroesListUseCase().map { pagingData ->
            pagingData.map { mapper.map(it) }
        }.cachedIn(viewModelScope)
    }
}
