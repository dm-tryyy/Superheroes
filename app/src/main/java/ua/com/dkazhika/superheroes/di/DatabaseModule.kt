package ua.com.dkazhika.superheroes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.com.dkazhika.superheroes.data.heroeslist.local.HeroesDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): HeroesDb {
        return Room.databaseBuilder(context, HeroesDb::class.java, "heroes").build()
    }
}