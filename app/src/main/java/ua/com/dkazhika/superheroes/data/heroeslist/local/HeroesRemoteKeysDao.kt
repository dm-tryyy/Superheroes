package ua.com.dkazhika.superheroes.data.heroeslist.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroesRemoteKeysDao {

    @Query("SELECT * FROM heroesRemoteKeys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): HeroesRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<HeroesRemoteKeys>)

    @Query("DELETE FROM heroesRemoteKeys")
    suspend fun deleteAllRemoteKeys()
}