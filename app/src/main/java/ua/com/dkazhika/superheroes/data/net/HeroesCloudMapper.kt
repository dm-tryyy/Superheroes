package ua.com.dkazhika.superheroes.data.net

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.HeroData
import ua.com.dkazhika.superheroes.data.net.servermodels.CharacterCloud

interface HeroesCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<CharacterCloud>): List<HeroData>

    class Base(private val heroMapper: HeroCloudToDataMapper) : HeroesCloudMapper {
        override fun map(cloudList: List<CharacterCloud>) = cloudList.map { heroCloud ->
            heroCloud.map(heroMapper)
        }
    }
}