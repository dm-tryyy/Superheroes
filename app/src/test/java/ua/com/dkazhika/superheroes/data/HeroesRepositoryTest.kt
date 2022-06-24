package ua.com.dkazhika.superheroes.data

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesDataContainer
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesRepository
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroDb
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.HeroesCloudMapper
import ua.com.dkazhika.superheroes.data.servermodels.*
import java.net.UnknownHostException

class HeroesRepositoryTest : BaseHeroesRepositoryTest() {

    private val comicsList = ComicList(listOf(ComicSummary("comic's name")), 1)
    private val unknownHostException = UnknownHostException()

    private val results = listOf(
        CharacterCloud(1, "name1", "description1", Image("path1", "jpg"), comicsList),
        CharacterCloud(2, "name2", "description2", Image("path2", "jpg"), comicsList),
        CharacterCloud(3, "name3", "description3", Image("path3", "jpg"), comicsList)
    )

    private val charactersDataContainer = CharactersDataContainer(results)
    private val characterDataWrapper = CharacterDataWrapper(charactersDataContainer)

    @Test
    fun `getting list of heroes without internet and cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroCloudToDataMapper(TestImageMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesDataContainer.Fail(unknownHostException)

        assertEquals(expected, actual)
    }

    @Test
    fun `getting list of heroes with internet and without cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroCloudToDataMapper(TestImageMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesDataContainer.Success(
            listOf(
                HeroData(1, "name1", "path1.jpg"),
                HeroData(2, "name2", "path2.jpg"),
                HeroData(3, "name3", "path3.jpg")
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `getting list of heroes without internet and with cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroCloudToDataMapper(TestImageMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesDataContainer.Success(
            listOf(
                HeroData(10, "name10", "image10"),
                HeroData(11, "name20", "image20"),
                HeroData(12, "name30", "image30")
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `getting list of heroes with internet and with cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = true)
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroCloudToDataMapper(TestImageMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesDataContainer.Success(
            listOf(
                HeroData(10, "name10", "image10"),
                HeroData(11, "name20", "image20"),
                HeroData(12, "name30", "image30")
            )
        )

        assertEquals(expected, actual)
    }

    private inner class TestCloudDataSource(
        private val returnSuccess: Boolean
    ) : HeroesCloudDataSource {

        override suspend fun fetchHeroes(): CharacterDataWrapper {
            if (returnSuccess) {
                return characterDataWrapper
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestCacheDataSource(
        private val returnSuccess: Boolean
    ) : HeroesCacheDataSource {

        override fun fetchHeroes(): List<HeroDb> {
            return if (returnSuccess) {
                listOf(
                    HeroDb().apply {
                        id = 10
                        name = "name10"
                        imageUrl = "image10"
                    },
                    HeroDb().apply {
                        id = 11
                        name = "name20"
                        imageUrl = "image20"
                    },
                    HeroDb().apply {
                        id = 12
                        name = "name30"
                        imageUrl = "image30"
                    }
                )
            } else {
                emptyList()
            }
        }

        override fun saveHeroes(heroes: List<HeroData>) {
            // not used here
        }
    }

}