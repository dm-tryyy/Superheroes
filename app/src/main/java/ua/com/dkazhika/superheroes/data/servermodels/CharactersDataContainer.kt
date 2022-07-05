package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetails
import ua.com.dkazhika.superheroes.domain.heroeslist.Hero

@Keep
data class CharactersDataContainer(
    @SerializedName("results")
    val results: List<CharacterApi>
) {
    fun toHeroDetails(): HeroDetails = results[0].toHeroDetails()
}