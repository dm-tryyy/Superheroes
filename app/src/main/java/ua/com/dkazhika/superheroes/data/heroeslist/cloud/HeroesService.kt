package ua.com.dkazhika.superheroes.data.heroeslist.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import ua.com.dkazhika.superheroes.BuildConfig

interface HeroesService {

    @GET("characters?ts=$TIMESTAMP&apikey=$API_KEY&hash=$HASH")
    suspend fun fetchCharacters() : ResponseBody

    companion object { //todo add interceptor
        const val TIMESTAMP = BuildConfig.TIMESTAMP
        const val API_KEY = BuildConfig.API_KEY
        const val HASH = BuildConfig.HASH
    }
}