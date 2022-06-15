package ua.com.dkazhika.superheroes.core

import android.app.Application
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ua.com.dkazhika.superheroes.data.HeroDataToDbMapper
import ua.com.dkazhika.superheroes.data.HeroesRepository
import ua.com.dkazhika.superheroes.data.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.cache.RealmProvider
import ua.com.dkazhika.superheroes.data.net.*
import ua.com.dkazhika.superheroes.domain.BaseHeroDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.BaseHeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.HeroDomainToHeroUiMapper
import ua.com.dkazhika.superheroes.domain.HeroesInteractor
import ua.com.dkazhika.superheroes.presentation.*

class SuperheroesApp : Application() {

    private companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }


    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val service = retrofit.create(HeroesService::class.java)
        val gson = Gson()
        val cloudDataSource = HeroesCloudDataSource.Base(service, gson)
        val cacheDataSource = HeroesCacheDataSource.Base(RealmProvider.Base(), HeroDataToDbMapper.Base())
        val heroesCloudMapper =
            HeroesCloudMapper.Base(HeroCloudToDataMapper.Base(ThumbnailMapper.Base()))
        val heroesCacheMapper = HeroesCacheMapper.Base(HeroCacheMapper.Base())

        val heroesRepository = HeroesRepository.Base(
            cloudDataSource,
            cacheDataSource,
            heroesCloudMapper,
            heroesCacheMapper
        )
        val heroesInteractor =
            HeroesInteractor.Base(
                heroesRepository, BaseHeroesDataToDomainMapper(
                    BaseHeroDataToDomainMapper()
                )
            )

        val communication = HeroesCommunication.Base()
        mainViewModel = MainViewModel(
            heroesInteractor,
            BaseHeroesDomainToUiMapper(ResourceProvider.Base(this), BaseHeroDomainToHeroUiMapper()),
            communication
        )
    }
}