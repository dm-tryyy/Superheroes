package ua.com.dkazhika.superheroes.data.heroeslist.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.com.dkazhika.superheroes.data.servermodels.CharacterDataWrapper

interface HeroesCloudDataSource {

    suspend fun fetchHeroes(): CharacterDataWrapper

    class Base(
        private val service: HeroesService,
        private val gson: Gson
    ) : HeroesCloudDataSource {
        private val type = object : TypeToken<CharacterDataWrapper>() {}.type
        override suspend fun fetchHeroes(): CharacterDataWrapper = gson.fromJson(service.fetchCharacters().string(), type)
    }
}