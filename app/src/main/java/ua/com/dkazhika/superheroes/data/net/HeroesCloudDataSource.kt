package ua.com.dkazhika.superheroes.data.net

import ua.com.dkazhika.superheroes.data.net.servermodels.CharactersDataContainer

interface HeroesCloudDataSource {

    suspend fun fetchHeroes() : CharactersDataContainer

    class Base(private val service: HeroesService) : HeroesCloudDataSource {
        override suspend fun fetchHeroes(): CharactersDataContainer = service.fetchCharacters()
    }
}