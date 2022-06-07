package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.net.HeroesCloudMapper

@Keep
data class CharactersDataContainer(
    @SerializedName("results")
    private val results: List<CharacterCloud>
) : Abstract.Object<List<Hero>, HeroesCloudMapper>() {
    override fun map(mapper: HeroesCloudMapper): List<Hero> {
        return mapper.map(results)
    }
}