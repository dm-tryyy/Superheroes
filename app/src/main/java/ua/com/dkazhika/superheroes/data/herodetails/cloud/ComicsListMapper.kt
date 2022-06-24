package ua.com.dkazhika.superheroes.data.herodetails.cloud

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.servermodels.ComicSummary

interface ComicsListMapper : Abstract.Mapper {

    fun map(listComicSummary: List<ComicSummary>): List<String>

    class Base(private val mapper: ComicSummaryMapper) : ComicsListMapper {
        override fun map(listComicSummary: List<ComicSummary>): List<String> {
            return listComicSummary.map {
                it.map(mapper)
            }
        }
    }
}
