package ua.com.dkazhika.superheroes.data.heroeslist.cache

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.data.heroeslist.HeroData

interface HeroCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String, imageUrl: String): HeroData

    class Base : HeroCacheMapper {
        override fun map(id: Int, name: String, imageUrl: String): HeroData {
            return HeroData(id, name, imageUrl)
        }
    }
}