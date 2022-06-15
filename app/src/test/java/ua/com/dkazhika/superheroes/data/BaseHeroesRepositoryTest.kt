package ua.com.dkazhika.superheroes.data
/*
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.net.HeroDataToDomainMapper
import ua.com.dkazhika.superheroes.data.net.ThumbnailMapper
import ua.com.dkazhika.superheroes.data.net.servermodels.Thumbnail

abstract class BaseHeroesRepositoryTest {
    protected inner class TestHeroCacheMapper : HeroCacheMapper {

        override fun map(id: Int, name: String, description: String, imageUrl: String): Hero {
            return Hero(id, name, description, imageUrl)
        }
    }

    protected inner class TestHeroDataToDomainMapper(
        private val thumbnailMapper: ThumbnailMapper
    ) : HeroDataToDomainMapper {

        override fun map(id: Int, name: String, description: String, thumbnail: Thumbnail): Hero {
            val imageUrl: String = thumbnail.map(thumbnailMapper)
            return Hero(id, name, description, imageUrl)
        }
    }

    protected inner class TestThumbnailMapper : ThumbnailMapper {
        override fun map(path: String, extension: String) = "$path.$extension"
    }
}*/