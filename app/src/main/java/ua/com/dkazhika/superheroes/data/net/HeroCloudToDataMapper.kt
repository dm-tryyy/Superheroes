package ua.com.dkazhika.superheroes.data.net

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.HeroData
import ua.com.dkazhika.superheroes.data.net.servermodels.Thumbnail

interface HeroCloudToDataMapper : Abstract.Mapper {

    fun map(id: Int, name: String, description: String, thumbnail: Thumbnail): HeroData

    class Base(private val thumbnailMapper: ThumbnailMapper) : HeroCloudToDataMapper {
        override fun map(id: Int, name: String, description: String, thumbnail: Thumbnail): HeroData {
            val imageUrl: String = thumbnail.map(thumbnailMapper)
            return HeroData(id, name, description, imageUrl)
        }
    }
}