package ua.com.dkazhika.superheroes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ua.com.dkazhika.superheroes.SuperheroesApp
import ua.com.dkazhika.superheroes.data.HeroesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HeroesApi.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeroesApi(retrofit: Retrofit): HeroesApi {
        return retrofit.create(HeroesApi::class.java)
    }
}