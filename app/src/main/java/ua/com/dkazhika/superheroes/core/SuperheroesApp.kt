package ua.com.dkazhika.superheroes.core

import android.app.Application
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsRepository
import ua.com.dkazhika.superheroes.data.herodetails.cloud.*
import ua.com.dkazhika.superheroes.data.heroeslist.HeroDataToDbMapper
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesRepository
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroCacheMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheDataSource
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroesCacheMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cache.RealmProvider
import ua.com.dkazhika.superheroes.data.heroeslist.cloud.*
import ua.com.dkazhika.superheroes.domain.herodetails.BaseHeroDetailsDataContainerToDomainMapper
import ua.com.dkazhika.superheroes.domain.herodetails.BaseHeroDetailsDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsInteractor
import ua.com.dkazhika.superheroes.domain.heroeslist.BaseHeroDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.heroeslist.BaseHeroesDataToDomainMapper
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesInteractor
import ua.com.dkazhika.superheroes.presentation.*
import ua.com.dkazhika.superheroes.presentation.herodetails.BaseHeroDetailsDomainContainerToUiMapper
import ua.com.dkazhika.superheroes.presentation.herodetails.BaseHeroDetailsDomainToUiMapper
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsCommunication
import ua.com.dkazhika.superheroes.presentation.herodetails.HeroDetailsViewModel
import ua.com.dkazhika.superheroes.presentation.heroeslist.BaseHeroDomainToHeroUiMapper
import ua.com.dkazhika.superheroes.presentation.heroeslist.BaseHeroesDomainToUiMapper
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroesCommunication
import ua.com.dkazhika.superheroes.presentation.heroeslist.HeroesListViewModel

class SuperheroesApp : Application() {

    private companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }


    lateinit var heroesListViewModel: HeroesListViewModel
    lateinit var heroDetailsViewModel: HeroDetailsViewModel

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
        val heroDetailsService = retrofit.create(HeroDetailsService::class.java)
        val gson = Gson()
        val cloudDataSource = HeroesCloudDataSource.Base(service, gson)
        val heroDetailsCloudDataSource = HeroDetailsCloudDataSource.Base(heroDetailsService, gson)
        val cacheDataSource =
            HeroesCacheDataSource.Base(RealmProvider.Base(), HeroDataToDbMapper.Base())
        val heroesCloudMapper =
            HeroesCloudMapper.Base(HeroCloudToDataMapper.Base(ImageMapper.Base()))
        val heroDetailsCloudMapper = HeroDetailsCloudMapper.Base(
            HeroCloudToHeroDetailsMapper.Base(
                ImageMapper.Base(),
                ComicsListMapper.Base(ComicSummaryMapper.Base())
            )
        )
        val heroesCacheMapper = HeroesCacheMapper.Base(HeroCacheMapper.Base())

        val heroesRepository = HeroesRepository.Base(
            cloudDataSource,
            cacheDataSource,
            heroesCloudMapper,
            heroesCacheMapper
        )
        val heroDetailsRepository = HeroDetailsRepository.Base(
            heroDetailsCloudDataSource,
            heroDetailsCloudMapper
        )
        val heroesInteractor =
            HeroesInteractor.Base(
                heroesRepository, BaseHeroesDataToDomainMapper(
                    BaseHeroDataToDomainMapper()
                )
            )
        val heroDetailsInteractor =
            HeroDetailsInteractor.Base(
                heroDetailsRepository,
                BaseHeroDetailsDataContainerToDomainMapper(BaseHeroDetailsDataToDomainMapper())
            )

        val resourceProvider = ResourceProvider.Base(this)

        heroesListViewModel = HeroesListViewModel(
            heroesInteractor,
            BaseHeroesDomainToUiMapper(resourceProvider, BaseHeroDomainToHeroUiMapper()),
            HeroesCommunication.Base()
        )
        heroDetailsViewModel = HeroDetailsViewModel(
            heroDetailsInteractor,
            HeroDetailsCommunication.Base(),
            BaseHeroDetailsDomainContainerToUiMapper(BaseHeroDetailsDomainToUiMapper(), resourceProvider)
        )
    }
}