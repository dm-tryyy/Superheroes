package ua.com.dkazhika.superheroes.data

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.cache.DbWrapper
import ua.com.dkazhika.superheroes.data.cache.HeroDb

interface HeroDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, description: String, imageUrl: String, db: DbWrapper): HeroDb

    class Base : HeroDataToDbMapper {
        override fun mapToDb(
            id: Int,
            name: String,
            description: String,
            imageUrl: String,
            db: DbWrapper
        ): HeroDb =
            db.createObject(id).also {
                it.name = name
                it.description = description
                it.imageUrl = imageUrl
            }
    }
}