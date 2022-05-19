package ua.com.dkazhika.superheroes.core

import android.app.Application
import retrofit2.Retrofit
import ua.com.dkazhika.superheroes.data.HeroesRepository
import ua.com.dkazhika.superheroes.data.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.cache.RealmProvider
import ua.com.dkazhika.superheroes.data.net.*
import ua.com.dkazhika.superheroes.domain.BaseHeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.HeroesInteractor

class SuperheroesApp : Application() {

    private companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //todo log http calls
            .build()
        val service = retrofit.create(HeroesService::class.java)

        val cloudDataSource = HeroesCloudDataSource.Base(service)
        val cacheDataSource = HeroesCacheDataSource.Base(RealmProvider.Base())
        val heroesCloudMapper = HeroesCloudMapper.Base(HeroCloudMapper.Base(ThumbnailMapper.Base()))
        val heroesCacheMapper = HeroesCacheMapper.Base(HeroCacheMapper.Base())

        val heroesRepository = HeroesRepository.Base(
            cloudDataSource,
            cacheDataSource,
            heroesCloudMapper,
            heroesCacheMapper
        )
        val heroesInteractor = HeroesInteractor.Base(heroesRepository, BaseHeroesDataToDomainMapper())
    }
}