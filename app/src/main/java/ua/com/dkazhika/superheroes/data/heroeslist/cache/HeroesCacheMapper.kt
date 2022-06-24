package ua.com.dkazhika.superheroes.data.heroeslist.cache

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData

interface HeroesCacheMapper : Abstract.Mapper {

    fun map(cacheList: List<HeroDb>): List<HeroData>

    class Base(private val mapper: HeroCacheMapper) : HeroesCacheMapper {
        override fun map(cacheList: List<HeroDb>): List<HeroData> =
            cacheList.map { heroDb -> heroDb.map(mapper) }
    }
}