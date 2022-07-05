package ua.com.dkazhika.superheroes.data.heroeslist.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroesRemoteKeys")
data class HeroesRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)