package ua.com.dkazhika.superheroes.data.cache

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero

interface HeroCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String, description: String, imageUrl: String): Hero

    class Base : HeroCacheMapper {
        override fun map(id: Int, name: String, description: String, imageUrl: String): Hero {
            return Hero(id, name, description, imageUrl)
        }
    }
}