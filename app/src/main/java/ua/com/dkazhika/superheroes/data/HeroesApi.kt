package ua.com.dkazhika.superheroes.data

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.com.dkazhika.superheroes.BuildConfig
import ua.com.dkazhika.superheroes.data.heroeslist.paging.HeroesRemoteMediator.Companion.LOAD_LIMIT

interface HeroesApi {

    @GET("characters?ts=$TIMESTAMP&apikey=$API_KEY&hash=$HASH")
    suspend fun getHeroes(
        @Query("limit") limit: Int = LOAD_LIMIT,
        @Query("offset") offset: Int = 0
    ): ResponseBody

    @GET("characters/{characterId}?ts=$TIMESTAMP&apikey=$API_KEY&hash=$HASH")
    suspend fun getHeroDetails(@Path(value = "characterId") characterId: Int): ResponseBody

    companion object { //todo add interceptor
        const val TIMESTAMP = BuildConfig.TIMESTAMP
        const val API_KEY = BuildConfig.API_KEY
        const val HASH = BuildConfig.HASH
        const val BASE_URL = BuildConfig.BASE_URL
    }
}