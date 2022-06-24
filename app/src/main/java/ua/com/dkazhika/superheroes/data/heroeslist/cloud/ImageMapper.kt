package ua.com.dkazhika.superheroes.data.heroeslist.cloud

import ua.com.dkazhika.superheroes.core.Abstract

interface ImageMapper : Abstract.Mapper {

    fun map(path: String, extension: String): String

    class Base : ImageMapper {
        override fun map(path: String, extension: String) = "$path.$extension"
    }
}