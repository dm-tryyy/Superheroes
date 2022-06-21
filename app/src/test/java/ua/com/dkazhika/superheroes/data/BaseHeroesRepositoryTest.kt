package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.data.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.net.HeroCloudToDataMapper
import ua.com.dkazhika.superheroes.data.net.ThumbnailMapper
import ua.com.dkazhika.superheroes.data.net.servermodels.Thumbnail

abstract class BaseHeroesRepositoryTest {
    protected inner class TestHeroCacheMapper : HeroCacheMapper {

        override fun map(id: Int, name: String, description: String, imageUrl: String): HeroData {
            return HeroData(id, name, description, imageUrl)
        }
    }

    protected inner class TestHeroCloudToDataMapper(
        private val thumbnailMapper: ThumbnailMapper
    ) : HeroCloudToDataMapper {

        override fun map(id: Int, name: String, description: String, thumbnail: Thumbnail): HeroData {
            val imageUrl: String = thumbnail.map(thumbnailMapper)
            return HeroData(id, name, description, imageUrl)
        }
    }

    protected inner class TestThumbnailMapper : ThumbnailMapper {
        override fun map(path: String, extension: String) = "$path.$extension"
    }
}