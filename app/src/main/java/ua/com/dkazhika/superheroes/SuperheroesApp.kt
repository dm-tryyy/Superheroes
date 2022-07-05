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
class SuperheroesApp : Application()