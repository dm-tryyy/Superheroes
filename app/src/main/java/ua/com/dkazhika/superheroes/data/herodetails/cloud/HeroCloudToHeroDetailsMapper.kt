package ua.com.dkazhika.superheroes.data.herodetails.cloud

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsData
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.ImageMapper
import ua.com.dkazhika.superheroes.data.servermodels.ComicList
import ua.com.dkazhika.superheroes.data.servermodels.Image

interface HeroCloudToHeroDetailsMapper : Abstract.Mapper {

    fun map(id: Int, name: String, description: String, image: Image, comics: ComicList): HeroDetailsData

    class Base(
        private val imageMapper: ImageMapper,
        private val comicsListMapper: ComicsListMapper
    ) : HeroCloudToHeroDetailsMapper {
        override fun map(id: Int, name: String, description: String, image: Image, comics: ComicList): HeroDetailsData {
            val imageUrl = image.map(imageMapper)
            val comicsNames = comics.map(comicsListMapper)
            return HeroDetailsData(id, name, description, imageUrl, comicsNames)
        }
    }
}
