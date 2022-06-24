package ua.com.dkazhika.superheroes.data.heroeslist.cloud

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.servermodels.Image

interface HeroCloudToDataMapper : Abstract.Mapper {

    fun map(id: Int, name: String, image: Image): HeroData

    class Base(private val imageMapper: ImageMapper) : HeroCloudToDataMapper {
        override fun map(id: Int, name: String, image: Image): HeroData {
            val imageUrl: String = image.map(imageMapper)
            return HeroData(id, name, imageUrl)
        }
    }
}