package ua.com.dkazhika.superheroes.data.herodetails.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import ua.com.dkazhika.superheroes.BuildConfig

interface HeroDetailsService {

    @GET("characters/{characterId}?ts=$TIMESTAMP&apikey=$API_KEY&hash=$HASH")
    suspend fun fetchHeroDetails(@Path(value = "characterId") characterId: Int) : ResponseBody

    companion object { //todo add interceptor
        const val TIMESTAMP = BuildConfig.TIMESTAMP
        const val API_KEY = BuildConfig.API_KEY
        const val HASH = BuildConfig.HASH
    }
}