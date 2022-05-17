package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.net.HeroesCloudMapper

@Keep
data class CharactersDataContainer(
    @SerializedName("offset")
    private val offset: Int,
    @SerializedName("limit")
    private val limit: Int,
    @SerializedName("total")
    private val total: Int,
    @SerializedName("count")
    private val count: Int,
    @SerializedName("results")
    private val results: List<CharacterCloud>
) : Abstract.Object<List<Hero>, HeroesCloudMapper>() {
    override fun map(mapper: HeroesCloudMapper): List<Hero> {
        return mapper.map(results)
    }
}