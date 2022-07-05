package ua.com.dkazhika.superheroes.data.heroeslist.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeroDb::class, HeroesRemoteKeys::class], version = 1)
abstract class HeroesDb: RoomDatabase() {

    abstract fun heroesDao(): HeroesDao
    abstract fun heroesRemoteKeysDao(): HeroesRemoteKeysDao
}