package ua.com.dkazhika.superheroes.data.heroeslist.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroesDao {

    @Query("SELECT * FROM heroes")
    fun getHeroes(): PagingSource<Int, HeroDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHeroes(heroes: List<HeroDb>)

    @Query("DELETE FROM heroes")
    suspend fun deleteAllHeroes()

}