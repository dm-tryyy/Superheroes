package ua.com.dkazhika.superheroes.data

import io.realm.Realm
import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.cache.HeroDb

interface HeroDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, description: String, imageUrl: String, realm: Realm): HeroDb

    class Base : HeroDataToDbMapper {
        override fun mapToDb(
            id: Int,
            name: String,
            description: String,
            imageUrl: String,
            realm: Realm
        ): HeroDb =
            realm.createObject(HeroDb::class.java, id).also {
                it.name = name
                it.description = description
                it.imageUrl = imageUrl
            }
    }
}