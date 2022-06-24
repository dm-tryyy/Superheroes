package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.herodetails.cloud.HeroCloudToHeroDetailsMapper
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsData
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroCloudToDataMapper

@Keep
data class CharacterCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("description")
    private val description: String,
    @SerializedName("thumbnail")
    private val image: Image,
    @SerializedName("comics")
    private val comics: ComicList,
) : Abstract.Object<HeroData, HeroCloudToDataMapper>, ToHeroDetailsData<HeroDetailsData, HeroCloudToHeroDetailsMapper> {
    override fun map(mapper: HeroCloudToDataMapper) = mapper.map(id, name, image)

    override fun mapToHeroDetailsData(mapper: HeroCloudToHeroDetailsMapper) = mapper.map(id, name, description, image, comics)
}

interface ToHeroDetailsData<T, M : Abstract.Mapper> {
    fun mapToHeroDetailsData(mapper: HeroCloudToHeroDetailsMapper): T
}