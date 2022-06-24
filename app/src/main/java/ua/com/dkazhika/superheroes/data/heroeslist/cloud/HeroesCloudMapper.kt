package ua.com.dkazhika.superheroes.data.heroeslist.cloud

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.servermodels.CharacterCloud

interface HeroesCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<CharacterCloud>): List<HeroData>

    class Base(private val heroMapper: HeroCloudToDataMapper) : HeroesCloudMapper {
        override fun map(cloudList: List<CharacterCloud>) = cloudList.map { heroCloud ->
            heroCloud.map(heroMapper)
        }
    }
}