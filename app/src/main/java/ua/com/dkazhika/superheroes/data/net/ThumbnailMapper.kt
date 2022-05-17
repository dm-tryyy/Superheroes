package ua.com.dkazhika.superheroes.data.net

import ua.com.dkazhika.superheroes.core.Abstract

interface ThumbnailMapper : Abstract.Mapper {

    fun map(path: String, extension: String): String

    class Base() : ThumbnailMapper {
        override fun map(path: String, extension: String) = "$path$extension"
    }
}