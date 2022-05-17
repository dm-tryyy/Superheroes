package ua.com.dkazhika.superheroes.data.cache

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero

interface HeroesCacheMapper : Abstract.Mapper {

    fun map(cacheList: List<HeroDb>): List<Hero>

    class Base(private val mapper: HeroCacheMapper) : HeroesCacheMapper {
        override fun map(cacheList: List<HeroDb>): List<Hero> =
            cacheList.map { heroDb -> heroDb.map(mapper) }
    }
}