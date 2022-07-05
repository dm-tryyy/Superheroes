package ua.com.dkazhika.superheroes.data.heroeslist.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.com.dkazhika.superheroes.domain.heroeslist.Hero


@Entity(tableName = "heroes")
data class HeroDb(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    var imageUrl: String
) {
    fun toHero() : Hero = Hero(id, name, imageUrl)
}
