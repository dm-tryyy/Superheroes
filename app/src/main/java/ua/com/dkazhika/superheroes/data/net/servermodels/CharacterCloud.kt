package ua.com.dkazhika.superheroes.data.net.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.net.HeroCloudMapper

@Keep
data class CharacterCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("description")
    private val description: String,
    @SerializedName("thumbnail")
    private val thumbnail: Thumbnail,
    @SerializedName("resourceURI")
    private val resourceURI: String,
    @SerializedName("comics")
    private val comics: ComicsServerModel,
    @SerializedName("series")
    private val series: Series,
    @SerializedName("stories")
    private val stories: Stories,
    @SerializedName("events")
    private val events: Events,
    @SerializedName("urls")
    private val urls: List<Url>
) : Abstract.Object<Hero, HeroCloudMapper>() {
    override fun map(mapper: HeroCloudMapper) = mapper.map(id, name, description, thumbnail)
}