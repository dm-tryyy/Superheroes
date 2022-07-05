package ua.com.dkazhika.superheroes.domain.heroeslist

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetHeroesListUseCase {

    operator fun invoke(): Flow<PagingData<Hero>>

    class Base @Inject constructor(
        private val heroesRepository: HeroesRepository,
    ) : GetHeroesListUseCase {
        override operator fun invoke() = heroesRepository.getHeroes()
    }
}