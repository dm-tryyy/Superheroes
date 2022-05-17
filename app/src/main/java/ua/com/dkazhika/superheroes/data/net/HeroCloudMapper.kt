package ua.com.dkazhika.superheroes.data.net

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.net.servermodels.Thumbnail

interface HeroCloudMapper : Abstract.Mapper {

    fun map(id: Int, name: String, description: String, thumbnail: Thumbnail): Hero

    class Base(private val thumbnailMapper: ThumbnailMapper) : HeroCloudMapper {
        override fun map(id: Int, name: String, description: String, thumbnail: Thumbnail): Hero {
            val imageUrl: String = thumbnail.map(thumbnailMapper)
            return Hero(id, name, description, imageUrl)
        }
    }
}