package ua.com.dkazhika.superheroes.data.herodetails.cloud

import ua.com.dkazhika.superheroes.core.Abstract

interface ComicSummaryMapper  : Abstract.Mapper {

    fun map(name: String): String

    class Base : ComicSummaryMapper {
        override fun map(name: String): String {
            return name
        }
    }

}
