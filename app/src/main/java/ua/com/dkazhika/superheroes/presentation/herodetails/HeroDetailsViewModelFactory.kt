package ua.com.dkazhika.superheroes.presentation.herodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.com.dkazhika.superheroes.domain.herodetails.GetHeroDetailsUseCase
import java.lang.IllegalArgumentException

class HeroDetailsViewModelFactory(
    private val heroDetailsUseCase: GetHeroDetailsUseCase,
    private val mapper: HeroDetailsToHeroUiMapper.Base,
    private val heroId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroDetailsViewModel::class.java)) {
            return HeroDetailsViewModel(heroDetailsUseCase, mapper, heroId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
