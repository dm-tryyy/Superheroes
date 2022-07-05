package ua.com.dkazhika.superheroes

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ua.com.dkazhika.superheroes.data.herodetails.HeroDetailsRepositoryImpl
import ua.com.dkazhika.superheroes.data.heroeslist.ErrorHandlerImpl
import ua.com.dkazhika.superheroes.data.heroeslist.HeroesRepositoryImpl
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroesDb
import ua.com.dkazhika.superheroes.data.herodetails.remote.HeroDetailsRemoteDataSource
import ua.com.dkazhika.superheroes.data.HeroesApi
import ua.com.dkazhika.superheroes.data.heroeslist.remote.HeroesRemoteDataSource
import ua.com.dkazhika.superheroes.domain.herodetails.HeroDetailsRepository
import ua.com.dkazhika.superheroes.domain.heroeslist.HeroesRepository

@HiltAndroidApp
class SuperheroesApp : Application() {

    private companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }

    lateinit var heroesRepository: HeroesRepository
    lateinit var heroDetailsRepository: HeroDetailsRepository


    override fun onCreate() {
        super.onCreate()

        val database = Room.databaseBuilder(this, HeroesDb::class.java, "heroes").build()
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val api = retrofit.create(HeroesApi::class.java)
        val remoteDataSource = HeroesRemoteDataSource.Base(api, database)
        val heroDetailsRemoteDataSource = HeroDetailsRemoteDataSource.Base(api)
        val errorHandler = ErrorHandlerImpl()
        heroesRepository = HeroesRepositoryImpl(
            remoteDataSource
        )
        heroDetailsRepository = HeroDetailsRepositoryImpl(
            heroDetailsRemoteDataSource,
            errorHandler
        )
    }
}