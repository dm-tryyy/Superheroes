package ua.com.dkazhika.superheroes.data.herodetails.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.com.dkazhika.superheroes.data.HeroesApi
import ua.com.dkazhika.superheroes.data.servermodels.CharacterDataWrapper

interface HeroDetailsRemoteDataSource {

    suspend fun getHeroDetails(id: Int): CharacterDataWrapper

    class Base(
        private val api: HeroesApi
    ) : HeroDetailsRemoteDataSource {
        private val type = object : TypeToken<CharacterDataWrapper>() {}.type
        override suspend fun getHeroDetails(id: Int): CharacterDataWrapper =
            Gson().fromJson(api.getHeroDetails(id).string(), type)
    }
}