package ua.com.dkazhika.superheroes.core

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import ua.com.dkazhika.superheroes.data.HeroesRepository
import ua.com.dkazhika.superheroes.data.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.cache.RealmProvider
import ua.com.dkazhika.superheroes.data.net.*
import ua.com.dkazhika.superheroes.domain.BaseHeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.HeroesInteractor
import ua.com.dkazhika.superheroes.presentation.BaseHeroesDomainToUiMapper
import ua.com.dkazhika.superheroes.presentation.HeroesCommunication
import ua.com.dkazhika.superheroes.presentation.MainViewModel
import ua.com.dkazhika.superheroes.presentation.ResourceProvider

class SuperheroesApp : Application() {

    private companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }


    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
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

        val communication = HeroesCommunication.Base()
        mainViewModel = MainViewModel(
            heroesInteractor,
            BaseHeroesDomainToUiMapper(communication, ResourceProvider.Base(this)),
            communication
        )
    }
}