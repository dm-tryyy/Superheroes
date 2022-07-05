package ua.com.dkazhika.superheroes.domain.heroeslist

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GetHeroesListUseCase {

    operator fun invoke(): Flow<PagingData<Hero>>

    class Base(
        private val heroesRepository: HeroesRepository,
    ) : GetHeroesListUseCase {
        override operator fun invoke() = heroesRepository.getHeroes()
    }
}