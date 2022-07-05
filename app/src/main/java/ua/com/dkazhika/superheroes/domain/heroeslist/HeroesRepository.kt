package ua.com.dkazhika.superheroes.domain.heroeslist

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {

    fun getHeroes(): Flow<PagingData<Hero>>

}