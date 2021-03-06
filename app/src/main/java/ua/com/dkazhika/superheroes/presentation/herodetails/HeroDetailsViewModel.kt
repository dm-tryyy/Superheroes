package ua.com.dkazhika.superheroes.presentation.herodetails

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.dkazhika.superheroes.domain.ErrorType
import ua.com.dkazhika.superheroes.domain.herodetails.GetHeroDetailsUseCase
import ua.com.dkazhika.superheroes.domain.herodetails.Result
import javax.inject.Inject

class HeroDetailsViewModel @AssistedInject constructor(
    private val getHeroDetailsUseCase: GetHeroDetailsUseCase,
    private val mapper: HeroDetailsToHeroUiMapper,
    @Assisted heroId: Int
) : ViewModel() {

    private val _state: MutableLiveData<HeroDetailsUiState> = MutableLiveData()
    val state: LiveData<HeroDetailsUiState> = _state

    init {
        fetchHeroDetails(heroId)
    }

    fun fetchHeroDetails(id: Int) {
        _state.value = HeroDetailsUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = getHeroDetailsUseCase.invoke(id)
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        val heroDetailsUi = mapper.map(result.heroDetails)
                        _state.value = HeroDetailsUiState.Success(heroDetailsUi)
                    }
                    is Result.Fail -> {
                        val errorMessage = when (result.error) {
                            is ErrorType.NoConnection -> "No internet connection"
                            is ErrorType.ServiceUnavailable -> "Service unavailable"
                            is ErrorType.Unknown -> "Something went wrong"
                        }
                        _state.value =
                            HeroDetailsUiState.Fail(errorMessage)
                    }
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(heroId: Int) : HeroDetailsViewModel
    }
}

sealed class HeroDetailsUiState {
    object Loading : HeroDetailsUiState()
    data class Success(val heroDetails: HeroDetailsUi) : HeroDetailsUiState()
    data class Fail(val errorMessage: String) : HeroDetailsUiState()
}
