package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroDb
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetails
import ua.com.dkazhika.superheroes.domain.heroeslist.Hero

@Keep
data class CharacterApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val image: Image,
    @SerializedName("comics")
    val comics: ComicList,
) {

    fun toHeroDetails() = HeroDetails(id, name, description, image.toImageUrl(), comics.toComicsList())

    fun toHeroDb() = HeroDb(id = id, name = name, imageUrl = image.toImageUrl())
}