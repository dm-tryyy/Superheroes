package ua.com.dkazhika.superheroes.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.heroeslist.HeroDataToDbMapper
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesDataContainer
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesRepository
import ua.com.dkazhika.superheroes.data.heroeslist.cache.DbWrapper
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroDb
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudMapper
import ua.com.dkazhika.superheroes.data.servermodels.CharacterCloud
import ua.com.dkazhika.superheroes.data.servermodels.CharacterDataWrapper
import ua.com.dkazhika.superheroes.data.servermodels.CharactersDataContainer
import ua.com.dkazhika.superheroes.data.servermodels.Image
/*
class HeroesRepositorySaveHeroesTest : BaseHeroesRepositoryTest() {

    private val results = listOf(
        CharacterCloud(1, "name1", "description1", Image("path1", "jpg")),
        CharacterCloud(2, "name2", "description2", Image("path2", "jpg")),
        CharacterCloud(3, "name3", "description3", Image("path3", "jpg"))
    )
    private val charactersDataContainer = CharactersDataContainer(results)
    private val characterDataWrapper = CharacterDataWrapper(charactersDataContainer)

    @Test
    fun `test saving heroes into cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource()
        val testCacheDataSource = TestCacheDataSource()
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroCloudToDataMapper(TestImageMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actualCloud = repository.fetchHeroes()
        val expectedCloud = HeroesDataContainer.Success(
            listOf(
                HeroData(1, "name1", "description1", "path1.jpg"),
                HeroData(2, "name2", "description2", "path2.jpg"),
                HeroData(3, "name3", "description3", "path3.jpg")
            )
        )

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchHeroes()
        val expectedCache = HeroesDataContainer.Success(
            listOf(
                HeroData(1, "name1 db", "description1", "path1.jpg"),
                HeroData(2, "name2 db", "description2", "path2.jpg"),
                HeroData(3, "name3 db", "description3", "path3.jpg")
            )
        )

        assertEquals(expectedCache, actualCache)

    }

    private inner class TestCloudDataSource : HeroesCloudDataSource {

        override suspend fun fetchHeroes(): CharacterDataWrapper {
            return characterDataWrapper
        }
    }

    private inner class TestCacheDataSource : HeroesCacheDataSource {

        private val list = ArrayList<HeroDb>()

        override fun fetchHeroes(): List<HeroDb> {
            return list
        }

        override fun saveHeroes(heroes: List<HeroData>) {
            heroes.map { hero ->
                list.add(
                    hero.mapTo(
                        object : HeroDataToDbMapper {
                            override fun mapToDb(
                                id: Int,
                                name: String,
                                description: String,
                                imageUrl: String,
                                db: DbWrapper
                            ) = HeroDb().apply {
                                this.id = id
                                this.name = "$name db"
                                this.description = description
                                this.imageUrl = imageUrl
                            }
                        }, object : DbWrapper {
                            override fun createObject(id: Int): HeroDb = HeroDb().apply {
                                this.id = id
                            }
                        })
                )
            }
        }
    }
}*/