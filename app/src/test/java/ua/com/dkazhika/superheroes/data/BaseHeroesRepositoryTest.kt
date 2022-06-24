package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroCloudToDataMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.ImageMapper
import ua.com.dkazhika.superheroes.data.servermodels.Image

abstract class BaseHeroesRepositoryTest {
    protected inner class TestHeroCacheMapper : HeroCacheMapper {

        override fun map(id: Int, name: String, imageUrl: String): HeroData {
            return HeroData(id, name, imageUrl)
        }
    }

    protected inner class TestHeroCloudToDataMapper(
        private val imageMapper: ImageMapper
    ) : HeroCloudToDataMapper {

        override fun map(id: Int, name: String, image: Image): HeroData {
            val imageUrl: String = image.map(imageMapper)
            return HeroData(id, name, imageUrl)
        }
    }

    protected inner class TestImageMapper : ImageMapper {
        override fun map(path: String, extension: String) = "$path.$extension"
    }
}