package ua.com.dkazhika.superheroes.data.herodetails

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.HeroDataToDbMapper
import ua.com.dkazhika.superheroes.data.heroeslist.cache.DbWrapper
import ua.com.dkazhika.superheroes.data.heroeslist.cache.HeroDb

interface HeroDetailsDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, description: String, imageUrl: String, comicsNames: List<String>, db: DbWrapper): HeroDb

    class Base : HeroDataToDbMapper {
        override fun mapToDb(
            id: Int,
            name: String,
            imageUrl: String,
            db: DbWrapper
        ): HeroDb =
            db.createObject(id).also {
                it.name = name
                it.imageUrl = imageUrl
            }
    }
}