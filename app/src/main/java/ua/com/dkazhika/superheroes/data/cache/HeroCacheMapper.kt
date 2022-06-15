package ua.com.dkazhika.superheroes.data.cache

import ua.com.dkazhika.superheroes.core.Abstract
import ua.com.dkazhika.superheroes.core.Hero
import ua.com.dkazhika.superheroes.data.HeroData

interface HeroCacheMapper : Abstract.Mapper {

    fun map(id: Int, name: String, description: String, imageUrl: String): HeroData

    class Base : HeroCacheMapper {
        override fun map(id: Int, name: String, description: String, imageUrl: String): HeroData {
            return HeroData(id, name, description, imageUrl)
        }
    }
}