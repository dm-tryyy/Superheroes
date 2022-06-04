package ua.com.dkazhika.superheroes.data.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.com.dkazhika.superheroes.data.net.servermodels.CharacterDataWrapper

interface HeroesCloudDataSource {

    suspend fun fetchHeroes(): CharacterDataWrapper

    class Base(private val service: HeroesService) : HeroesCloudDataSource {
        private val gson = Gson()
        private val type = object : TypeToken<CharacterDataWrapper>() {}.type
        override suspend fun fetchHeroes(): CharacterDataWrapper = gson.fromJson(service.fetchCharacters().string(), type)
    }
}