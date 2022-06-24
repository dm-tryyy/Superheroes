package ua.com.dkazhika.superheroes.data.herodetails.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.com.dkazhika.superheroes.data.servermodels.CharacterDataWrapper

interface HeroDetailsCloudDataSource {

    suspend fun fetchHeroDetails(id: Int): CharacterDataWrapper

    class Base(
        private val service: HeroDetailsService,
        private val gson: Gson
    ) : HeroDetailsCloudDataSource {
        private val type = object : TypeToken<CharacterDataWrapper>() {}.type
        override suspend fun fetchHeroDetails(id: Int): CharacterDataWrapper =
            gson.fromJson(service.fetchHeroDetails(id).string(), type)
    }
}