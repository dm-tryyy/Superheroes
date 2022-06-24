package ua.com.dkazhika.superheroes.data.servermodels

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsData
import ua.com.dkazhika.superheroes.data.herodetails.cloud.HeroDetailsCloudMapper
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudMapper

@Keep
data class CharactersDataContainer(
    @SerializedName("results")
    private val results: List<CharacterCloud>
) : Abstract.Object<List<HeroData>, HeroesCloudMapper>,
    ToListHeroDetailsData<HeroDetailsData, HeroDetailsCloudMapper> {
    override fun map(mapper: HeroesCloudMapper): List<HeroData> {
        return mapper.map(results)
    }

    override fun mapToHeroDetailsData(mapper: HeroDetailsCloudMapper): HeroDetailsData {
        return mapper.map(results[0])
    }
}

interface ToListHeroDetailsData<T, M : Abstract.Mapper> {
    fun mapToHeroDetailsData(mapper: HeroDetailsCloudMapper) : T
}