package ua.com.dkazhika.superheroes.data.herodetails.cloud

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsData
import ua.com.dkazhika.superheroes.data.servermodels.CharacterCloud

interface HeroDetailsCloudMapper : Abstract.Mapper {

    fun map(character: CharacterCloud): HeroDetailsData

    class Base(private val heroCloudToHeroDetailsMapper: HeroCloudToHeroDetailsMapper) :
        HeroDetailsCloudMapper {
        override fun map(character: CharacterCloud) =
            character.mapToHeroDetailsData(heroCloudToHeroDetailsMapper)
    }
}