package ua.com.dkazhika.superheroes.data.net

import retrofit2.http.GET
import ua.com.dkazhika.superheroes.data.net.servermodels.CharactersDataContainer

interface HeroesService {

    @GET("characters")
    suspend fun fetchCharacters() : CharactersDataContainer
}