package ua.com.dkazhika.superheroes.data

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.cache.HeroDb
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.net.HeroesCloudDataSource
import ua.com.dkazhika.superheroes.data.net.HeroesCloudMapper
import ua.com.dkazhika.superheroes.data.net.servermodels.*
import java.net.UnknownHostException
/*
class HeroesRepositoryTest : BaseHeroesRepositoryTest() {

    private val results = listOf(
        CharacterCloud(1, "name1", "description1", Thumbnail("path1", "jpg")),
        CharacterCloud(2, "name2", "description2", Thumbnail("path2", "jpg")),
        CharacterCloud(3, "name3", "description3", Thumbnail("path3", "jpg"))
    )
    private val charactersDataContainer = CharactersDataContainer(results)
    private val characterDataWrapper = CharacterDataWrapper(charactersDataContainer)
    private val unknownHostException = UnknownHostException()

    @Test
    fun `getting list of heroes without internet and cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroDataToDomainMapper(TestThumbnailMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesData.Fail(unknownHostException)

        assertEquals(expected, actual)
    }

    @Test
    fun `getting list of heroes with internet and without cache`() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestCacheDataSource(returnSuccess = false)
        val repository = HeroesRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            HeroesCloudMapper.Base(TestHeroDataToDomainMapper(TestThumbnailMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesData.Success(
            listOf(
                Hero(1, "name1", "description1", "path1.jpg"),
                Hero(2, "name2", "description2", "path2.jpg"),
                Hero(3, "name3", "description3", "path3.jpg")
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
            HeroesCloudMapper.Base(TestHeroDataToDomainMapper(TestThumbnailMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesData.Success(
            listOf(
                Hero(10, "name10", "description10", "image10"),
                Hero(11, "name20", "description20", "image20"),
                Hero(12, "name30", "description30", "image30")
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
            HeroesCloudMapper.Base(TestHeroDataToDomainMapper(TestThumbnailMapper())),
            HeroesCacheMapper.Base(TestHeroCacheMapper())
        )

        val actual = repository.fetchHeroes()
        val expected = HeroesData.Success(
            listOf(
                Hero(10, "name10", "description10", "image10"),
                Hero(11, "name20", "description20", "image20"),
                Hero(12, "name30", "description30", "image30")
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
                        description = "description10"
                        imageUrl = "image10"
                    },
                    HeroDb().apply {
                        id = 11
                        name = "name20"
                        description = "description20"
                        imageUrl = "image20"
                    },
                    HeroDb().apply {
                        id = 12
                        name = "name30"
                        description = "description30"
                        imageUrl = "image30"
                    }
                )
            } else {
                emptyList()
            }
        }

        override fun saveHeroes(heroes: List<Hero>) {
            // not used here
        }
    }

}*/